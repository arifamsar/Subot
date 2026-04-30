package com.subot.core.domain.usecase

import com.subot.core.domain.model.AuthToken
import com.subot.core.domain.model.AuthUser
import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.model.UserProfileSummary
import com.subot.core.domain.repository.AuthRepository
import com.subot.core.domain.repository.LoginAs
import com.subot.core.domain.result.ApiResult
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoginUseCaseTest {

    private lateinit var fakeRepository: FakeAuthRepository
    private lateinit var loginUseCase: LoginUseCase

    @BeforeTest
    fun setup() {
        fakeRepository = FakeAuthRepository()
        loginUseCase = LoginUseCase(fakeRepository)
    }

    @Test
    fun `invoke with empty identifier returns Error`() = runTest {
        // Act
        val result = loginUseCase(LoginAs.MEMBER, "", "password123")

        // Assert
        assertTrue(result is ApiResult.Error)
        assertEquals("Identifier cannot be empty", result.message)
    }

    @Test
    fun `invoke with empty password returns Error`() = runTest {
        // Act
        val result = loginUseCase(LoginAs.MEMBER, "12345", "")

        // Assert
        assertTrue(result is ApiResult.Error)
        assertEquals("Password cannot be empty", result.message)
    }

    @Test
    fun `invoke with valid credentials returns Success and saves token`() = runTest {
        // Arrange
        val validToken = AuthToken(
            tokenType = "Bearer",
            accessToken = "dummy_access_token",
            userType = "member",
            user = AuthUser("member", UserProfileSummary(1, "12345"))
        )
        fakeRepository.setLoginResult(ApiResult.Success(validToken))

        // Act
        val result = loginUseCase(LoginAs.MEMBER, "12345", "password123")

        // Assert
        assertTrue(result is ApiResult.Success)
        assertEquals(validToken, result.data)
        
        // Verify side effects
        assertEquals("dummy_access_token", fakeRepository.savedToken)
        assertTrue(fakeRepository.isLoggedIn)
    }

    @Test
    fun `invoke with invalid credentials returns Error from API and does not save token`() = runTest {
        // Arrange
        fakeRepository.setLoginResult(ApiResult.Error("Invalid credentials", 401))

        // Act
        val result = loginUseCase(LoginAs.MEMBER, "12345", "wrong_password")

        // Assert
        assertTrue(result is ApiResult.Error)
        assertEquals("Invalid credentials", result.message)
        assertEquals(401, result.code)
        
        // Verify side effects didn't occur
        assertEquals(null, fakeRepository.savedToken)
        assertEquals(false, fakeRepository.isLoggedIn)
    }
}

/**
 * A simple in-memory Fake repository for testing purposes.
 */
class FakeAuthRepository : AuthRepository {
    
    var savedToken: String? = null
        private set
        
    var isLoggedIn: Boolean = false
        private set

    private var loginResult: ApiResult<AuthToken> = ApiResult.Error("Not configured")

    fun setLoginResult(result: ApiResult<AuthToken>) {
        loginResult = result
    }

    override suspend fun login(
        loginAs: LoginAs,
        identifier: String,
        password: String,
        deviceName: String
    ): ApiResult<AuthToken> {
        return loginResult
    }

    override suspend fun getMe(): ApiResult<UserProfile> {
        TODO("Not needed for LoginUseCase test")
    }

    override suspend fun logout(): ApiResult<Unit> {
        savedToken = null
        isLoggedIn = false
        return ApiResult.Success(Unit)
    }

    override suspend fun getAccessToken(): String? = savedToken

    override suspend fun saveAccessToken(token: String) {
        savedToken = token
    }

    override suspend fun clearAccessToken() {
        savedToken = null
    }

    override suspend fun setLoggedIn(loggedIn: Boolean) {
        this.isLoggedIn = loggedIn
    }

    private var userRole: String? = null

    override suspend fun saveUserRole(role: String) {
        userRole = role
    }

    override suspend fun getUserRole(): String? = userRole

    override suspend fun clearUserRole() {
        userRole = null
    }
}