package com.subot.core.data.repository

import com.subot.core.data.dto.AuthTokenDto
import com.subot.core.data.dto.LoginRequestDto
import com.subot.core.data.dto.UserProfileDto
import com.subot.core.data.mapper.toDomain
import com.subot.core.data.service.ApiService
import com.subot.core.data.service.UserPreferences
import com.subot.core.data.util.safeApiCall
import com.subot.core.domain.model.AuthToken
import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.repository.AuthRepository
import com.subot.core.domain.repository.LoginAs
import com.subot.core.domain.result.ApiResult

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override suspend fun login(
        loginAs: LoginAs,
        identifier: String,
        password: String,
        deviceName: String
    ): ApiResult<AuthToken> {
        val request = LoginRequestDto(
            loginAs = loginAs.value,
            identifier = identifier,
            password = password,
            deviceName = deviceName
        )
        val result = safeApiCall<AuthTokenDto> { apiService.login(request) }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun getMe(): ApiResult<UserProfile> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<UserProfileDto> { apiService.getMe(token) }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun logout(): ApiResult<Unit> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Success(Unit) // Already logged out locally
        return try {
            apiService.logout(token)
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Logout failed")
        }
    }

    override suspend fun getAccessToken(): String? = userPreferences.getAccessToken()

    override suspend fun saveAccessToken(token: String) = userPreferences.setAccessToken(token)

    override suspend fun clearAccessToken() = userPreferences.clearAccessToken()

    override suspend fun setLoggedIn(loggedIn: Boolean) = userPreferences.setLoggedIn(loggedIn)
}

