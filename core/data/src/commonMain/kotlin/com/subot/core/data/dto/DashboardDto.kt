package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponseDto(
    @SerialName("status")
    val status: String = "",
    @SerialName("message")
    val message: String = "",
    @SerialName("data")
    val data: DashboardDto? = null
)

@Serializable
data class DashboardDto(
    @SerialName("user")
    val user: UserDto? = null,
    @SerialName("summary_metrics")
    val summaryMetrics: SummaryMetricsDto? = null,
    @SerialName("schedule_preview")
    val schedulePreview: List<SchedulePreviewDto> = emptyList(),
    @SerialName("unpaid_invoices")
    val unpaidInvoices: UnpaidInvoicesDto? = null
)

@Serializable
data class UserDto(
    @SerialName("display_name")
    val displayName: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("profile_image_url")
    val profileImageUrl: String? = null
)

@Serializable
data class SummaryMetricsDto(
    @SerialName("totalSchedules")
    val totalSchedules: Int = 0,
    @SerialName("completedSchedules")
    val completedSchedules: Int = 0,
    @SerialName("remainingSchedules")
    val remainingSchedules: Int = 0,
    @SerialName("nextSchedule")
    val nextSchedule: NextScheduleDto? = null
)

@Serializable
data class NextScheduleDto(
    @SerialName("dateLabel")
    val dateLabel: String = "",
    @SerialName("timeRange")
    val timeRange: String = "",
    @SerialName("program")
    val program: String = "",
    @SerialName("trainer")
    val trainer: String = ""
)

@Serializable
data class SchedulePreviewDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("program")
    val program: String = "",
    @SerialName("trainer")
    val trainer: String = "",
    @SerialName("date")
    val date: String = "",
    @SerialName("start_time")
    val startTime: String = "",
    @SerialName("end_time")
    val endTime: String = "",
    @SerialName("time_range")
    val timeRange: String = "",
    @SerialName("date_label")
    val dateLabel: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("status_label")
    val statusLabel: String = "",
    @SerialName("status_badges")
    val statusBadges: List<String> = emptyList(),
    @SerialName("row_classes")
    val rowClasses: String = ""
)

@Serializable
data class UnpaidInvoicesDto(
    @SerialName("items")
    val items: List<InvoiceItemDto> = emptyList(),
    @SerialName("total_amount")
    val totalAmount: Long = 0L,
    @SerialName("total_formatted")
    val totalFormatted: String = "",
    @SerialName("has_more")
    val hasMore: Boolean = false,
    @SerialName("remaining_count")
    val remainingCount: Int = 0
)

@Serializable
data class InvoiceItemDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("label")
    val label: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("amount")
    val amount: Long = 0L,
    @SerialName("amount_formatted")
    val amountFormatted: String = "",
    @SerialName("due_date_formatted")
    val dueDateFormatted: String = ""
)

@Serializable
data class MeetingDto(
    @SerialName("trainer_name")
    val trainerName: String = "",
    @SerialName("time")
    val time: String = "",
    @SerialName("description")
    val description: String = ""
)
