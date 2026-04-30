package com.subot.core.domain.usecase

import com.subot.core.domain.model.ScheduleDetail
import com.subot.core.domain.repository.ScheduleRepository
import com.subot.core.domain.result.ApiResult

class GetScheduleDetailUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(id: Int): ApiResult<ScheduleDetail> = scheduleRepository.getScheduleDetail(id)
}
