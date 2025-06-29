package com.reringuy.sync.utils

import kotlinx.datetime.LocalDateTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

private val DateFormat_dd_MM_yyyy = LocalDateTime.Format {
    day(padding = Padding.SPACE)
    // Format as two digits (e.g., 01, 25)
    char('/')
    monthNumber(padding = Padding.SPACE) // Format as two digits (e.g., 01, 12)
    char('/')
    year(padding = Padding.SPACE) // Format as four digits (e.g., 2025)
}

fun Instant.formatToString(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    val localDateTime = this.toLocalDateTime(timeZone)
    return localDateTime.format(DateFormat_dd_MM_yyyy)
}