package com.mcxiaoke.koi.assert

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 16:50
 */


fun <T : Any> throwIfNull(obj: T?, message: String? = "object is null") {
    if (obj == null) {
        throw IllegalArgumentException(message)
    }
}

fun throwIfEmpty(obj: String?, message: String? = "string is null or empty") {
    if (obj == null || obj.isEmpty()) {
        throw IllegalArgumentException(message)
    }
}

fun <T : Any> throwIfEmpty(obj: Collection<T>?, message: String? = "collection is null or empty") {
    if (obj == null || obj.isEmpty()) {
        throw IllegalArgumentException(message)
    }
}

fun throwIfFalse(condition: Boolean, message: String? = "condition is false") {
    if (!condition) {
        throw IllegalArgumentException(message)
    }
}

fun throwIfTrue(condition: Boolean, message: String? = "condition is true") {
    if (condition) {
        throw IllegalArgumentException(message)
    }
}
