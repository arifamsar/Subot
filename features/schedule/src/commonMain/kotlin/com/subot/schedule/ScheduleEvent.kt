package com.subot.schedule

sealed class ScheduleEvent {
    object LoadSchedules : ScheduleEvent()
    data class LoadScheduleDetail(val id: Int) : ScheduleEvent()
    object Refresh : ScheduleEvent()
}
