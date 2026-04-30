package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDto(
    @SerialName("id")
    val id: Int,
    @SerialName("trainer_name")
    val trainerName: String,
    @SerialName("time")
    val time: String,
    @SerialName("description")
    val description: String,
    @SerialName("status")
    val status: String
)

@Serializable
data class ScheduleDetailDto(
    @SerialName("id")
    val id: Int,
    @SerialName("trainer_name")
    val trainerName: String,
    @SerialName("time")
    val time: String,
    @SerialName("description")
    val description: String,
    @SerialName("material")
    val material: String,
    @SerialName("attendance_list")
    val attendanceList: List<AttendanceDto>,
    @SerialName("location")
    val location: String? = null
)

@Serializable
data class AttendanceDto(
    @SerialName("id")
    val id: Int,
    @SerialName("member_name")
    val memberName: String,
    @SerialName("status")
    val status: String
)
