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
    trainerName = trainerName,
    time = time,
    description = description,
    material = material,
    attendanceList = attendanceList.map { it.toDomain() },
    location = location
)

fun AttendanceDto.toDomain(): Attendance = Attendance(
    id = id,
    memberName = memberName,
    status = status
)
