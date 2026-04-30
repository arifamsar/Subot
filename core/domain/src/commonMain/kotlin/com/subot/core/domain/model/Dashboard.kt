package com.subot.core.domain.model

data class Dashboard(
    val userName: String,
    val schoolName: String,
    val upcomingMeeting: NextSchedule?,
    val remainingMeetingsCount: Int,
    val hasUnpaidBills: Boolean,
    val totalUnpaidAmount: Long,
    val schedulePreview: List<SchedulePreview> = emptyList(),
    val unpaidInvoiceCount: Int = 0,
    val totalUnpaidFormatted: String = "Rp 0"
)

data class SchedulePreview(
    val id: Int,
    val program: String,
    val trainer: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val timeRange: String,
    val dateLabel: String,
    val status: String,
    val statusLabel: String,
    val statusBadges: List<String>,
    val rowClasses: String
)

data class NextSchedule(
    val dateLabel: String,
    val timeRange: String,
    val program: String,
    val trainer: String
)
