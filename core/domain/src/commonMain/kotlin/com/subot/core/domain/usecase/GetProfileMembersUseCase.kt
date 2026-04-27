package com.subot.core.domain.usecase

import androidx.paging.PagingData
import com.subot.core.domain.model.Member
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.repository.ProfileRepository
import com.subot.core.domain.result.ApiResult
import kotlinx.coroutines.flow.Flow

class GetProfileMembersUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        perPage: Int = 10,
        search: String? = null
    ): ApiResult<PaginatedData<Member>> {
        return profileRepository.getProfileMembers(
            page = page,
            perPage = perPage,
            search = search
        )
    }

    fun invokeFlow(
        perPage: Int = 10,
        search: String? = null
    ): Flow<PagingData<Member>> {
        return profileRepository.getProfileMembersPaged(
            perPage = perPage,
            search = search
        )
    }
}
