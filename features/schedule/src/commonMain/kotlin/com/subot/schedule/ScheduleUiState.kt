package com.subot.schedule

import com.subot.core.domain.model.Schedule
import com.subot.core.domain.model.ScheduleDetail

data class ScheduleUiState(
    val isLoading: Boolean = false,
    val schedules: List<Schedule> = emptyList(),
    val scheduleDetail: ScheduleDetail? = null,
    val error: String? = null,
    val userRole: String? = null
)
