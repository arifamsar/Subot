package com.subot.core.domain.repository

import androidx.paging.PagingData
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.School
import com.subot.core.domain.result.ApiResult
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    /**
     * Fetches a single page of schools (no auth required).
     */
    suspend fun getSchools(page: Int = 1, perPage: Int = 10): ApiResult<PaginatedData<School>>

    /**
     * Returns a cold [Flow] of [PagingData] for infinite-scroll / paginated school lists.
     * @param perPage Items per page (default 20)
     */
    fun getSchoolsPaged(perPage: Int = 20): Flow<PagingData<School>>
}

