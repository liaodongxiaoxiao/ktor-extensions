package com.karl.coder.ktor.extensions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.lang.Exception
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


fun Timestamp?.toStr(): String? {
    return if (this == null) {
        null
    } else {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.format(this)
    }
}

fun String?.toDateTime(): DateTime {
    if (this == null) {
        return DateTime()
    }
    return try {
        val formatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
        formatter.parseDateTime(this)
    } catch (e: Exception) {
        DateTime()
    }

}

fun Date.toStr(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val format = SimpleDateFormat(pattern)
    return format.format(this)
}