package com.subot.core.domain.usecase

import com.subot.core.domain.model.PenanggungJawab
import com.subot.core.domain.repository.ProfileRepository
import com.subot.core.domain.result.ApiResult

class UpdatePenanggungJawabUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(
        namaPenanggungJawab: String,
        emailPenanggungJawab: String,
        telephonePenanggungJawab: String
    ): ApiResult<PenanggungJawab> {
        return profileRepository.updatePenanggungJawab(
            namaPenanggungJawab = namaPenanggungJawab,
            emailPenanggungJawab = emailPenanggungJawab,
            telephonePenanggungJawab = telephonePenanggungJawab
        )
    }
}
