package com.mcxiaoke.koi.core

import android.os.Looper

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 16:50
 */


fun <T : Any> notNull(obj: T?, message: String? = "argument is null") {
    if (obj == null) {
        throw IllegalArgumentException(message)
    }
}

fun notEmpty(obj: String?, message: String? = "argument is empty") {
    if (obj == null || obj.length == 0) {
        throw IllegalArgumentException(message)
    }
}

fun isTrue(condition: Boolean, message: String? = "argument is false") {
    if (!condition) {
        throw IllegalArgumentException(message)
    }
}

fun isFalse(condition: Boolean, message: String? = "argument is true") {
    if (condition) {
        throw IllegalArgumentException(message)
    }
}

fun isMainThread(): Boolean {
    return Looper.myLooper() == Looper.getMainLooper()
}