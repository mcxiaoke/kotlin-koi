package com.mcxiaoke.koi.log

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:29
 */

import android.content.Context
import android.util.Log
import com.mcxiaoke.koi.KoiConfig
import com.mcxiaoke.koi.core.threadName

fun Throwable.stackTraceString(): String = Log.getStackTraceString(this)

private fun logMessageWithThreadName(message: String): String
        = "$message [T:${threadName()}]"

fun Context.logv(message: String) {
    logv(javaClass.simpleName, message)
}

fun Context.logd(message: String) {
    logd(javaClass.simpleName, message)
}

fun Context.logi(message: String) {
    logi(javaClass.simpleName, message)
}

fun Context.logw(message: String) {
    logw(javaClass.simpleName, message)
}

fun Context.loge(message: String) {
    loge(javaClass.simpleName, message)
}

fun Context.logf(message: String) {
    logf(javaClass.simpleName, message)
}

fun logv(tag: String, message: String, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.VERBOSE) {
        Log.v(tag, logMessageWithThreadName(message), exception)
    }
}

fun logd(tag: String, message: String, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.DEBUG) {
        Log.d(tag, logMessageWithThreadName(message), exception)
    }
}

fun logi(tag: String, message: String, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.INFO) {
        Log.i(tag, logMessageWithThreadName(message), exception)
    }
}

fun logw(tag: String, message: String, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.WARN) {
        Log.w(tag, logMessageWithThreadName(message), exception)
    }
}

fun loge(tag: String, message: String, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.ERROR) {
        Log.e(tag, logMessageWithThreadName(message), exception)
    }
}

fun logf(tag: String, message: String, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.ERROR) {
        Log.wtf(tag, logMessageWithThreadName(message), exception)
    }
}

fun logw(tag: String, exception: Throwable?) {
    if (KoiConfig.logLevel <= Log.WARN) {
        Log.w(tag, logMessageWithThreadName("warn"), exception)
    }
}

fun logf(tag: String, exception: Throwable?) {
    if (KoiConfig.logLevel <= Log.ERROR) {
        Log.wtf(tag, logMessageWithThreadName("wtf"), exception)
    }
}

inline fun Context.logv(lazyMessage: () -> Any?) {
    logv(javaClass.simpleName, lazyMessage)
}

inline fun Context.logd(lazyMessage: () -> Any?) {
    logd(javaClass.simpleName, lazyMessage)
}

inline fun Context.logi(lazyMessage: () -> Any?) {
    logi(javaClass.simpleName, lazyMessage)
}

inline fun Context.logw(lazyMessage: () -> Any?) {
    logw(javaClass.simpleName, lazyMessage)
}

inline fun Context.loge(lazyMessage: () -> Any?) {
    loge(javaClass.simpleName, lazyMessage)
}

inline fun Context.logf(lazyMessage: () -> Any?) {
    logf(javaClass.simpleName, lazyMessage)
}

inline fun logv(tag: String, lazyMessage: () -> Any?, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.VERBOSE) {
        Log.v(tag, (lazyMessage()?.toString() ?: "null") + " [T:${threadName()}]", exception)
    }
}

inline fun logd(tag: String, lazyMessage: () -> Any?, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.DEBUG) {
        Log.d(tag, (lazyMessage()?.toString() ?: "null") + " [T:${threadName()}]", exception)
    }
}

inline fun logi(tag: String, lazyMessage: () -> Any?, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.INFO) {
        Log.i(tag, (lazyMessage()?.toString() ?: "null") + " [T:${threadName()}]", exception)
    }
}

inline fun logw(tag: String, lazyMessage: () -> Any?, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.WARN) {
        Log.w(tag, (lazyMessage()?.toString() ?: "null") + " [T:${threadName()}]", exception)
    }
}

inline fun loge(tag: String, lazyMessage: () -> Any?, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.ERROR) {
        Log.e(tag, (lazyMessage()?.toString() ?: "null") + " [T:${threadName()}]", exception)
    }
}

inline fun logf(tag: String, lazyMessage: () -> Any?, exception: Throwable? = null) {
    if (KoiConfig.logLevel <= Log.ERROR) {
        Log.wtf(tag, (lazyMessage()?.toString() ?: "null") + " [T:${threadName()}]", exception)
    }
}