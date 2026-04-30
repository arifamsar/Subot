package com.subot.core.domain.model

data class Schedule(
    val id: Int,
    val trainerName: String,
    val time: String,
    val description: String,
    val status: String
)

data class ScheduleDetail(
    val id: Int,
    val trainerName: String,
    val time: String,
    val description: String,
    val material: String,
    val attendanceList: List<Attendance>,
    val location: String?
)

data class Attendance(
    val id: Int,
    val memberName: String,
    val status: String
)
