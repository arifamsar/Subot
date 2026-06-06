package com.subot.schedule

sealed class ScheduleEvent {
    object LoadSchedules : ScheduleEvent()
    data class LoadScheduleDetail(val id: Int) : ScheduleEvent()
    object Refresh : ScheduleEvent()
    data class ExportReport(
        val scheduleIds: List<Int>?,
        val startDate: String?,
        val endDate: String?
    ) : ScheduleEvent()
    object ClearExportResult : ScheduleEvent()
}
