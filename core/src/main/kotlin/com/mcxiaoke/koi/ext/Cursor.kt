package com.mcxiaoke.koi.ext

import android.database.Cursor

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 16:57
 */
fun Cursor.intValue(columnName: String): Int {
    return getInt(getColumnIndexOrThrow(columnName))
}

fun Cursor.longValue(columnName: String): Long {
    return getLong(getColumnIndexOrThrow(columnName))
}

fun Cursor.stringValue(columnName: String): String {
    return getString(getColumnIndexOrThrow(columnName))
}

fun Cursor.booleanValue(columnName: String): Boolean {
    return getInt(getColumnIndexOrThrow(columnName)) != 0
}

inline fun <T> Cursor?.map(transform: Cursor.() -> T): MutableCollection<T> {
    return mapTo(arrayListOf<T>(), transform)
}

inline fun <T, R : MutableCollection<T>> Cursor?.mapTo(result: R, transform: Cursor.() -> T): R {
    return if (this == null) result else {
        if (moveToFirst())
            do {
                result.add(transform())
            } while (moveToNext())
        result
    }
}

inline fun <T> Cursor?.mapAndClose(transform: Cursor.() -> T): MutableCollection<T> {
    try {
        return map(transform)
    } finally {
        this?.close()
    }
}
