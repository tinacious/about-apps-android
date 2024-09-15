package com.tinaciousdesign.aboutapps.utils

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun formattedDateFromMillis(
    timestamp: Long,
    pattern: String = "MMM d, yyyy 'at' h:mm a",
): String {
    val instant = Instant.ofEpochMilli(timestamp)
    val zoneId = ZoneId.systemDefault()
    val zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId)
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return formatter.format(zonedDateTime)
}
