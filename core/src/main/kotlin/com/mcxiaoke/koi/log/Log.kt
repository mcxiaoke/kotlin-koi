package com.mcxiaoke.koi.log

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:29
 */

import android.util.Log
import com.mcxiaoke.koi.core.threadName

object KoiLogger {
    var LOG_LEVEL = Log.ASSERT
}

fun Throwable.stackTraceString(): String = Log.getStackTraceString(this)

private fun logMessage(lazyMessage: () -> Any?): String
        = (lazyMessage()?.toString() ?: "null") + " [T:${threadName()}]"

private fun logMessage(message: String): String
        = "$message [T:${threadName()}]"

fun Any.logv(message: String) {
    logv(this.javaClass.simpleName, message)
}

fun Any.logd(message: String) {
    logd(this.javaClass.simpleName, message)
}

fun Any.logi(message: String) {
    logi(this.javaClass.simpleName, message)
}

fun Any.logw(message: String) {
    logw(this.javaClass.simpleName, message)
}

fun Any.loge(message: String) {
    loge(this.javaClass.simpleName, message)
}

fun Any.logf(message: String) {
    logf(this.javaClass.simpleName, message)
}

fun Any.logv(tag: String, message: String) {
    logv(tag, message, null)
}

fun Any.logd(tag: String, message: String) {
    logd(tag, message, null)
}

fun Any.logi(tag: String, message: String) {
    logi(tag, message, null)
}

fun Any.logw(tag: String, message: String) {
    logw(tag, message, null)
}

fun Any.loge(tag: String, message: String) {
    loge(tag, message, null)
}

fun Any.logf(tag: String, message: String) {
    logf(tag, message, null)
}

fun Any.logv(tag: String, message: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.VERBOSE) {
        Log.v(tag, logMessage(message), exception)
    }
}

fun Any.logd(tag: String, message: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.DEBUG) {
        Log.d(tag, logMessage(message), exception)
    }
}

fun Any.logi(tag: String, message: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.INFO) {
        Log.i(tag, logMessage(message), exception)
    }
}

fun Any.logw(tag: String, message: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.WARN) {
        Log.w(tag, logMessage(message), exception)
    }
}

fun Any.loge(tag: String, message: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.ERROR) {
        Log.e(tag, logMessage(message), exception)
    }
}

fun Any.logf(tag: String, message: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.ERROR) {
        Log.wtf(tag, logMessage(message), exception)
    }
}

fun Any.logw(tag: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.WARN) {
        Log.w(tag, logMessage("warn"), exception)
    }
}

fun Any.logf(tag: String, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.ERROR) {
        Log.wtf(tag, logMessage("wtf"), exception)
    }
}

fun Any.logv(lazyMessage: () -> Any?) {
    logv(this.javaClass.simpleName, lazyMessage)
}

fun Any.logd(lazyMessage: () -> Any?) {
    logd(this.javaClass.simpleName, lazyMessage)
}

fun Any.logi(lazyMessage: () -> Any?) {
    logi(this.javaClass.simpleName, lazyMessage)
}

fun Any.logw(lazyMessage: () -> Any?) {
    logw(this.javaClass.simpleName, lazyMessage)
}

fun Any.loge(lazyMessage: () -> Any?) {
    loge(this.javaClass.simpleName, lazyMessage)
}

fun Any.logf(lazyMessage: () -> Any?) {
    logf(this.javaClass.simpleName, lazyMessage)
}

fun Any.logv(tag: String, lazyMessage: () -> Any?) {
    logv(tag, lazyMessage, null)
}

fun Any.logd(tag: String, lazyMessage: () -> Any?) {
    logd(tag, lazyMessage, null)
}

fun Any.logi(tag: String, lazyMessage: () -> Any?) {
    logi(tag, lazyMessage, null)
}

fun Any.logw(tag: String, lazyMessage: () -> Any?) {
    logw(tag, lazyMessage, null)
}

fun Any.loge(tag: String, lazyMessage: () -> Any?) {
    loge(tag, lazyMessage, null)
}

fun Any.logf(tag: String, lazyMessage: () -> Any?) {
    logf(tag, lazyMessage, null)
}

fun Any.logv(tag: String, lazyMessage: () -> Any?, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.VERBOSE) {
        Log.v(tag, logMessage(lazyMessage), exception)
    }
}

fun Any.logd(tag: String, lazyMessage: () -> Any?, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.DEBUG) {
        Log.d(tag, logMessage(lazyMessage), exception)
    }
}

fun Any.logi(tag: String, lazyMessage: () -> Any?, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.INFO) {
        Log.i(tag, logMessage(lazyMessage), exception)
    }
}

fun Any.logw(tag: String, lazyMessage: () -> Any?, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.WARN) {
        Log.w(tag, logMessage(lazyMessage), exception)
    }
}

fun Any.loge(tag: String, lazyMessage: () -> Any?, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.ERROR) {
        Log.e(tag, logMessage(lazyMessage), exception)
    }
}

fun Any.logf(tag: String, lazyMessage: () -> Any?, exception: Throwable?) {
    if (KoiLogger.LOG_LEVEL <= Log.ERROR) {
        Log.wtf(tag, logMessage(lazyMessage), exception)
    }
}