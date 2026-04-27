package com.subot.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.subot.core.data.dto.PaginatedMembersDto
import com.subot.core.data.mapper.toDomain
import com.subot.core.data.service.ApiService
import com.subot.core.data.service.UserPreferences
import com.subot.core.data.util.safeApiCall
import com.subot.core.domain.model.Member
import com.subot.core.domain.result.ApiResult

class MembersPagingSource(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences,
    private val search: String? = null,
    private val perPage: Int = 20
) : PagingSource<Int, Member>() {

    override fun getRefreshKey(state: PagingState<Int, Member>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Member> {
        val page = params.key ?: 1
        val token = userPreferences.getAccessToken()
            ?: return LoadResult.Error(Exception("Not authenticated"))
        return when (val result = safeApiCall<PaginatedMembersDto> {
            apiService.getProfileMembers(token = token, page = page, perPage = perPage, search = search)
        }) {
            is ApiResult.Success -> {
                val data = result.data
                val members = data.items.map { it.toDomain() }
                LoadResult.Page(
                    data = members,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (data.pagination.hasMorePages) page + 1 else null
                )
            }
            is ApiResult.Error -> LoadResult.Error(Exception(result.message))
            is ApiResult.Loading -> LoadResult.Error(Exception("Unexpected loading state"))
        }
    }
}
