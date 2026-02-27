package com.subot.core.domain.usecase

import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.repository.AuthRepository
import com.subot.core.domain.result.ApiResult

class GetAuthMeUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): ApiResult<UserProfile> = authRepository.getMe()
}

