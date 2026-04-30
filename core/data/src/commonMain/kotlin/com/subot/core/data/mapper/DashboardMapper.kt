package com.subot.core.data.mapper

import com.subot.core.data.dto.DashboardDto
import com.subot.core.data.dto.NextScheduleDto
import com.subot.core.data.dto.SchedulePreviewDto
import com.subot.core.domain.model.Dashboard
import com.subot.core.domain.model.NextSchedule
import com.subot.core.domain.model.SchedulePreview

fun DashboardDto.toDomain(): Dashboard {
    val upcomingMeeting = summaryMetrics?.nextSchedule?.toDomain()
    
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
    program = program,
    trainer = trainer,
    date = date,
    startTime = startTime,
    endTime = endTime,
    timeRange = timeRange,
    dateLabel = dateLabel,
    status = status,
    statusLabel = statusLabel,
    statusBadges = statusBadges,
    rowClasses = rowClasses
)

fun NextScheduleDto.toDomain(): NextSchedule = NextSchedule(
    dateLabel = dateLabel,
    timeRange = timeRange,
    program = program,
    trainer = trainer
)
