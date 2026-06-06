package com.subot.core.domain.usecase

import com.subot.core.domain.repository.ProfileRepository
import com.subot.core.domain.result.ApiResult

class ChangePasswordUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(
        currentPassword: String,
        password: String,
        passwordConfirmation: String
    ): ApiResult<String> {
        return profileRepository.changePassword(
            currentPassword = currentPassword,
            password = password,
            passwordConfirmation = passwordConfirmation
        )
    }
}
