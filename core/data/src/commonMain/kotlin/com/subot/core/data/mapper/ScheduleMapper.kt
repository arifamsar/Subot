package com.subot.core.data.mapper

import com.subot.core.data.dto.AttendanceDto
import com.subot.core.data.dto.ScheduleDetailDto
import com.subot.core.data.dto.ScheduleDto
import com.subot.core.domain.model.Attendance
import com.subot.core.domain.model.Schedule
import com.subot.core.domain.model.ScheduleDetail

fun ScheduleDto.toDomain(): Schedule = Schedule(
    id = id,
    trainerName = trainerName,
    time = time,
    description = description,
    status = status
)

fun ScheduleDetailDto.toDomain(): ScheduleDetail = ScheduleDetail(
    id = id,
    dateLabel = dateLabel,
    program = program,
    level = level,
    classroom = classroom,
    trainer = trainer,
    timeRange = timeRange,
    statusBadges = statusBadges,
    notes = notes,
    students = students.map { it.toDomain() }
)

fun AttendanceDto.toDomain(): Attendance = Attendance(
    id = id,
    nis = nis,
    name = name,
    absensiStatus = absensiStatus,
    absensiLabel = absensiLabel,
    rowClass = rowClass
)
