package com.subot.core.domain.usecase

import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.repository.ProfileRepository
import com.subot.core.domain.result.ApiResult

class GetProfileUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(): ApiResult<UserProfile> = profileRepository.getProfile()
}
