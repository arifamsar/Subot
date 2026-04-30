package com.subot.core.domain.usecase

import com.subot.core.domain.model.Schedule
import com.subot.core.domain.repository.ScheduleRepository
import com.subot.core.domain.result.ApiResult

class GetSchedulesUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(): ApiResult<List<Schedule>> = scheduleRepository.getSchedules()
}
