package com.sukarobot.subot.utils

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

/**
 * Extension functions for handling date and time operations using kotlinx-datetime
 */

/**
 * Get the current date in the system's default time zone
 */
fun Clock.nowAsLocalDate(): LocalDate {
    return now().toLocalDateTime(TimeZone.currentSystemDefault()).date
}

/**
 * Get the current time in the system's default time zone
 */
fun Clock.nowAsLocalTime(): LocalTime {
    return now().toLocalDateTime(TimeZone.currentSystemDefault()).time
}

/**
 * Get the current date and time in the system's default time zone
 */
fun Clock.nowAsLocalDateTime(): LocalDateTime {
    return now().toLocalDateTime(TimeZone.currentSystemDefault())
}

/**
 * Format LocalDate to a readable string (e.g., "December 2025")
 */
fun LocalDate.toMonthYearString(): String {
    val monthName = month.name.lowercase().replaceFirstChar { it.uppercase() }
    return "$monthName $year"
}

/**
 * Format LocalDate to day name (e.g., "Mon", "Tue")
 */
fun LocalDate.toDayName(): String {
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Mon"
        DayOfWeek.TUESDAY -> "Tue"
        DayOfWeek.WEDNESDAY -> "Wed"
        DayOfWeek.THURSDAY -> "Thu"
        DayOfWeek.FRIDAY -> "Fri"
        DayOfWeek.SATURDAY -> "Sat"
        DayOfWeek.SUNDAY -> "Sun"
    }
}

/**
 * Format LocalDate to short day name (e.g., "M", "T", "W")
 */
fun LocalDate.toShortDayName(): String {
    return toDayName().take(1)
}

/**
 * Get the day of month as string (e.g., "25")
 */
fun LocalDate.toDayOfMonthString(): String {
    return day.toString()
}

/**
 * Format LocalTime to 12-hour format (e.g., "09:00 AM")
 */
fun LocalTime.to12HourFormat(): String {
    val hourIn12Format = when {
        hour == 0 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }
    val period = if (hour < 12) "AM" else "PM"
    val minuteStr = minute.toString().padStart(2, '0')
    return "${hourIn12Format.toString().padStart(2, '0')}:$minuteStr $period"
}

/**
 * Format LocalTime to 24-hour format (e.g., "09:00")
 */
fun LocalTime.to24HourFormat(): String {
    val hourStr = hour.toString().padStart(2, '0')
    val minuteStr = minute.toString().padStart(2, '0')
    return "$hourStr:$minuteStr"
}

/**
 * Format LocalDateTime to a readable string (e.g., "Wed, Dec 25, 2025 at 09:00 AM")
 */
fun LocalDateTime.toReadableString(): String {
    val dayName = date.toDayName()
    val monthName = date.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
    val day = date.day
    val year = date.year
    val time = time.to12HourFormat()
    return "$dayName, $monthName $day, $year at $time"
}

/**
 * Check if a date is today
 */
fun LocalDate.isToday(): Boolean {
    val today = Clock.System.nowAsLocalDate()
    return this == today
}

/**
 * Check if a date is in the past
 */
fun LocalDate.isPast(): Boolean {
    val today = Clock.System.nowAsLocalDate()
    return this < today
}

/**
 * Check if a date is in the future
 */
fun LocalDate.isFuture(): Boolean {
    val today = Clock.System.nowAsLocalDate()
    return this > today
}

/**
 * Get a range of dates starting from a given date
 * @param days Number of days to include in the range
 */
fun LocalDate.rangeTo(days: Int): List<LocalDate> {
    return (0 until days).map { this.plus(it, DateTimeUnit.DAY) }
}

/**
 * Get dates for the current week (starting from Monday)
 */
fun LocalDate.getCurrentWeekDates(): List<LocalDate> {
    val daysSinceMonday = when (dayOfWeek) {
        DayOfWeek.MONDAY -> 0
        DayOfWeek.TUESDAY -> 1
        DayOfWeek.WEDNESDAY -> 2
        DayOfWeek.THURSDAY -> 3
        DayOfWeek.FRIDAY -> 4
        DayOfWeek.SATURDAY -> 5
        DayOfWeek.SUNDAY -> 6
    }
    val monday = this.minus(daysSinceMonday, DateTimeUnit.DAY)
    return monday.rangeTo(7)
}

/**
 * Calculate duration between two LocalTime instances in minutes
 */
fun LocalTime.durationUntil(other: LocalTime): Int {
    val thisMinutes = hour * 60 + minute
    val otherMinutes = other.hour * 60 + other.minute
    return kotlin.math.abs(otherMinutes - thisMinutes)
}

/**
 * Format duration in minutes to readable string (e.g., "1 hour", "30 mins", "1h 30m")
 */
fun Int.toDurationString(): String {
    return when {
        this < 60 -> "$this mins"
        this % 60 == 0 -> "${this / 60} hour${if (this / 60 > 1) "s" else ""}"
        else -> {
            val hours = this / 60
            val minutes = this % 60
            "${hours}h ${minutes}m"
        }
    }
}

/**
 * Add minutes to LocalTime
 */
fun LocalTime.plusMinutes(minutes: Int): LocalTime {
    val totalMinutes = (hour * 60 + minute + minutes) % (24 * 60)
    return LocalTime(totalMinutes / 60, totalMinutes % 60)
}

/**
 * Parse time string in 12-hour format (e.g., "09:00 AM") to LocalTime
 */
fun String.parseTime12Hour(): LocalTime? {
    return try {
        val parts = this.split(" ")
        if (parts.size != 2) return null

        val timeParts = parts[0].split(":")
        if (timeParts.size != 2) return null

        val hour = timeParts[0].toIntOrNull() ?: return null
        val minute = timeParts[1].toIntOrNull() ?: return null
        val period = parts[1].uppercase()

        val hour24 = when (period) {
            "AM" if hour == 12 -> 0
            "PM" if hour != 12 -> hour + 12
            else -> hour
        }

        LocalTime(hour24, minute)
    } catch (e: Exception) {
        null
    }
}

/**
 * Get a list of dates for the next N days starting from today
 */
fun getNextDays(count: Int, startDate: LocalDate = Clock.System.nowAsLocalDate()): List<LocalDate> {
    return (0 until count).map { startDate.plus(it, DateTimeUnit.DAY) }
}

/**
 * Get start and end of the current day
 */
fun LocalDate.startOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime(0, 0))
}

fun LocalDate.endOfDay(): LocalDateTime {
    return LocalDateTime(this, LocalTime(23, 59, 59))
}

