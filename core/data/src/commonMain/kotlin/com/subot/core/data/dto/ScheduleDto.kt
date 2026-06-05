package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDto(
    @SerialName("id")
    val id: Int,
    @SerialName("trainer_name")
    val trainerName: String = "",
    @SerialName("time")
    val time: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("status")
    val status: String = "",
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
    @SerialName("status_label")
    val statusLabel: String = "",
    @SerialName("status_badges")
    val statusBadges: List<String> = emptyList(),
    @SerialName("row_classes")
    val rowClasses: String = ""
)

@Serializable
data class ScheduleDetailDto(
    @SerialName("id")
    val id: Int,
    @SerialName("dateLabel")
    val dateLabel: String = "",
    @SerialName("program")
    val program: String = "",
    @SerialName("level")
    val level: String = "",
    @SerialName("classroom")
    val classroom: String = "",
    @SerialName("trainer")
    val trainer: String = "",
    @SerialName("timeRange")
    val timeRange: String = "",
    @SerialName("statusBadges")
    val statusBadges: List<String> = emptyList(),
    @SerialName("notes")
    val notes: String = "",
    @SerialName("students")
    val students: List<AttendanceDto> = emptyList()
)

@Serializable
data class AttendanceDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nis")
    val nis: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("absensi_status")
    val absensiStatus: String = "",
    @SerialName("absensi_label")
    val absensiLabel: String = "",
    @SerialName("row_class")
    val rowClass: String = ""
)
