package com.subot.core.data.repository

import com.subot.core.data.dto.DashboardDto
import com.subot.core.data.mapper.toDomain
import com.subot.core.data.service.ApiService
import com.subot.core.data.service.UserPreferences
import com.subot.core.data.util.safeApiCall
import com.subot.core.domain.model.Dashboard
import com.subot.core.domain.repository.DashboardRepository
import com.subot.core.domain.result.ApiResult

class DashboardRepositoryImpl(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : DashboardRepository {
    override suspend fun getDashboard(): ApiResult<Dashboard> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<DashboardDto> {
            apiService.getDashboard(token = token)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }
}
