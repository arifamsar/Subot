package com.subot.core.domain.model

data class Dashboard(
    val userName: String,
    val schoolName: String,
    val upcomingMeeting: Meeting?,
    val remainingMeetingsCount: Int,
    val hasUnpaidBills: Boolean,
    val totalUnpaidAmount: Long,
    val schedulePreview: List<SchedulePreview> = emptyList(),
    val unpaidInvoiceCount: Int = 0,
    val totalUnpaidFormatted: String = "Rp 0"
)

data class SchedulePreview(
    val id: Int,
    val title: String,
    val date: String,
    val time: String
)

data class Meeting(
    val trainerName: String,
    val time: String,
    val description: String
)
