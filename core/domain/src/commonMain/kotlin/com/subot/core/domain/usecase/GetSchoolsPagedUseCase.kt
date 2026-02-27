package com.subot.core.domain.usecase

import androidx.paging.PagingData
import com.subot.core.domain.model.School
import com.subot.core.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow

class GetSchoolsPagedUseCase(private val schoolRepository: SchoolRepository) {
    operator fun invoke(perPage: Int = 20): Flow<PagingData<School>> {
        return schoolRepository.getSchoolsPaged(perPage)
    }
}

