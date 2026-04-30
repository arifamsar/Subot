package com.subot.core.domain.repository

import com.subot.core.domain.model.Schedule
import com.subot.core.domain.model.ScheduleDetail
import com.subot.core.domain.result.ApiResult

interface ScheduleRepository {
    suspend fun getSchedules(): ApiResult<List<Schedule>>
    suspend fun getScheduleDetail(id: Int): ApiResult<ScheduleDetail>
}
