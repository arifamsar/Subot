package com.subot.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.subot.core.data.dto.PaginatedSchoolsDto
import com.subot.core.data.service.ApiService
import com.subot.core.data.util.safeApiCall
import com.subot.core.domain.model.School
import com.subot.core.domain.result.ApiResult

class SchoolPagingSource(
    private val apiService: ApiService,
    private val perPage: Int = 20
) : PagingSource<Int, School>() {

    override fun getRefreshKey(state: PagingState<Int, School>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, School> {
        val page = params.key ?: 1
        return when (val result = safeApiCall<PaginatedSchoolsDto> {
            apiService.getSchools(page = page, perPage = perPage)
        }) {
            is ApiResult.Success -> {
                val data = result.data
                val schools = data.items.map { School(id = it.id, name = it.sekolah) }
                LoadResult.Page(
                    data = schools,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (data.pagination.hasMorePages) page + 1 else null
                )
            }
            is ApiResult.Error -> LoadResult.Error(Exception(result.message))
            is ApiResult.Loading -> LoadResult.Error(Exception("Unexpected loading state"))
        }
    }
}

