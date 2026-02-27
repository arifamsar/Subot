package com.subot.core.domain.usecase

import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.School
import com.subot.core.domain.repository.SchoolRepository
import com.subot.core.domain.result.ApiResult

class GetSchoolsUseCase(private val schoolRepository: SchoolRepository) {
    suspend operator fun invoke(page: Int = 1, perPage: Int = 10): ApiResult<PaginatedData<School>> {
        return schoolRepository.getSchools(page, perPage)
    }
}

