package com.subot.core.data.repository

import com.subot.core.data.dto.ScheduleDetailDto
import com.subot.core.data.dto.ScheduleDetailResponseDto
import com.subot.core.data.dto.ScheduleDto
import com.subot.core.data.mapper.toDomain
import com.subot.core.data.service.ApiService
import com.subot.core.data.service.UserPreferences
import com.subot.core.data.util.safeApiCall
import com.subot.core.domain.model.Schedule
import com.subot.core.domain.model.ScheduleDetail
import com.subot.core.domain.repository.ScheduleRepository
import com.subot.core.domain.result.ApiResult

class ScheduleRepositoryImpl(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : ScheduleRepository {
    override suspend fun getSchedules(): ApiResult<List<Schedule>> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<List<ScheduleDto>> {
            apiService.getSchedules(token = token)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.map { it.toDomain() })
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun getScheduleDetail(id: Int): ApiResult<ScheduleDetail> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<ScheduleDetailResponseDto> {
            apiService.getScheduleDetail(token = token, id = id)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.schedule.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }
}
