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
    val dateLabel: String,
    val program: String,
    val level: String,
    val classroom: String,
    val trainer: String,
    val timeRange: String,
    val statusBadges: List<String>,
    val notes: String,
    val students: List<Attendance>
)

data class Attendance(
    val id: Int,
    val nis: String,
    val name: String,
    val absensiStatus: String,
    val absensiLabel: String,
    val rowClass: String
)
