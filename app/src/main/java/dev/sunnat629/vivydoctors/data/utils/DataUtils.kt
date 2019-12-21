package dev.sunnat629.vivydoctors.data.utils

import org.joda.time.format.DateTimeFormat

const val TIME_FORMAT_EEEE_HH_MM = "EEEE HH:mm"

fun String.timeFormatToPattern(pattern: String): String? {
    return try {
        val times = this.split("/"
        )
        val dateTimeFormatter = DateTimeFormat.forPattern(pattern)
        dateTimeFormatter.toString()
    } catch (e: IllegalArgumentException) {
        return null
    }
}