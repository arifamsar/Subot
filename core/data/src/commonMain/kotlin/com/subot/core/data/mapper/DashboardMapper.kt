package com.subot.core.data.mapper

import com.subot.core.data.dto.DashboardDto
import com.subot.core.data.dto.MeetingDto
import com.subot.core.data.dto.SchedulePreviewDto
import com.subot.core.domain.model.Dashboard
import com.subot.core.domain.model.Meeting
import com.subot.core.domain.model.SchedulePreview

fun DashboardDto.toDomain(): Dashboard {
    // Use first schedule preview as upcoming meeting if available
    val upcomingMeeting = schedulePreview.firstOrNull()?.let { schedule ->
        Meeting(
            trainerName = schedule.title,
            time = schedule.time,
            description = schedule.date
        )
    }
    
    return Dashboard(
        userName = user?.displayName ?: "",
        schoolName = user?.displayName ?: "",
        upcomingMeeting = upcomingMeeting,
        remainingMeetingsCount = summaryMetrics?.remainingSchedules ?: 0,
        hasUnpaidBills = (unpaidInvoices?.totalAmount ?: 0) > 0,
        totalUnpaidAmount = unpaidInvoices?.totalAmount ?: 0L,
        schedulePreview = schedulePreview.map { it.toDomain() },
        unpaidInvoiceCount = unpaidInvoices?.remainingCount ?: 0,
        totalUnpaidFormatted = unpaidInvoices?.totalFormatted ?: "Rp 0"
    )
}

fun SchedulePreviewDto.toDomain(): SchedulePreview = SchedulePreview(
    id = id ?: 0,
    title = title,
    date = date,
    time = time
)

fun MeetingDto.toDomain(): Meeting = Meeting(
    trainerName = trainerName,
    time = time,
    description = description
)
