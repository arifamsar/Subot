package com.subot.core.domain.usecase

import com.subot.core.domain.model.AuthToken
import com.subot.core.domain.repository.AuthRepository
import com.subot.core.domain.repository.LoginAs
import com.subot.core.domain.result.ApiResult

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        loginAs: LoginAs,
        identifier: String,
        password: String,
        deviceName: String = "android-mobile"
    ): ApiResult<AuthToken> {
        if (identifier.isBlank()) return ApiResult.Error("Identifier cannot be empty")
        if (password.isBlank()) return ApiResult.Error("Password cannot be empty")
        val result = authRepository.login(loginAs, identifier, password, deviceName)
        if (result is ApiResult.Success) {
            authRepository.saveAccessToken(result.data.accessToken)
            val role = result.data.user.profile.role ?: result.data.userType
            authRepository.saveUserRole(role)
            authRepository.setLoggedIn(true)
        }
        return result
    }
}

