package com.subot.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.subot.core.data.dto.PaginatedSchoolsDto
import com.subot.core.data.mapper.toDomain
import com.subot.core.data.paging.SchoolPagingSource
import com.subot.core.data.service.ApiService
import com.subot.core.data.util.safeApiCall
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.School
import com.subot.core.domain.repository.SchoolRepository
import com.subot.core.domain.result.ApiResult
import kotlinx.coroutines.flow.Flow

class SchoolRepositoryImpl(
    private val apiService: ApiService
) : SchoolRepository {

    override suspend fun getSchools(page: Int, perPage: Int): ApiResult<PaginatedData<School>> {
        val result = safeApiCall<PaginatedSchoolsDto> {
            apiService.getSchools(page, perPage)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override fun getSchoolsPaged(perPage: Int): Flow<PagingData<School>> {
        return Pager(
            config = PagingConfig(
                pageSize = perPage,
                enablePlaceholders = false,
                prefetchDistance = perPage  // fetch next page when within one full page of the end
            ),
            pagingSourceFactory = { SchoolPagingSource(apiService, perPage) }
        ).flow
    }
}

