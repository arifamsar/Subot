package com.subot.core.domain.usecase

import com.subot.core.domain.repository.AuthRepository
import com.subot.core.domain.result.ApiResult

class LogoutUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): ApiResult<Unit> {
        val result = authRepository.logout()
        // Always clear local state, even if the API call fails
        authRepository.clearAccessToken()
        authRepository.setLoggedIn(false)
        return result
    }
}

