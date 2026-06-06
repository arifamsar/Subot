package com.subot.core.domain.usecase

import com.subot.core.domain.repository.ScheduleRepository
import com.subot.core.domain.result.ApiResult

class ExportScheduleReportUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(
        scheduleIds: List<Int>? = null,
        startDate: String? = null,
        endDate: String? = null
    ): ApiResult<ByteArray> = scheduleRepository.exportScheduleReport(scheduleIds, startDate, endDate)
}
