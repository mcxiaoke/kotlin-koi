package com.mcxiaoke.koi.ext

import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 00:22
 */
object DateHelper {
    const val DF_SIMPLE_STRING = "yyyy-MM-dd HH:mm:ss"
    @JvmField val DF_SIMPLE_FORMAT = object : ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat {
            return SimpleDateFormat(DF_SIMPLE_STRING, Locale.US)
        }
    }
}

fun dateNow(): String {
    return Date().asString()
}

fun dateParse(s: String): Date {
    val position = ParsePosition(0)
    return DateHelper.DF_SIMPLE_FORMAT.get().parse(s, position)
}

fun Date.asString(format: DateFormat): String {
    return format.format(this)
}

fun Date.asString(): String {
    return DateHelper.DF_SIMPLE_FORMAT.get().format(this)
}

fun Long.asDateString(): String {
    return Date(this).asString()
}
