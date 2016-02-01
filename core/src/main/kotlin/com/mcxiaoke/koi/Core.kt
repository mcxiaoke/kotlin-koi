package com.mcxiaoke.koi

import android.util.Log
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:07
 */

class KoiConfig {
    companion object {
        var logLevel = Log.ASSERT
        var logEnabled: Boolean
            get() {
                return logLevel < Log.ASSERT
            }
            set(value) {
                logLevel = if (value) Log.VERBOSE else Log.ASSERT
            }
    }
}


fun threadName(): String = Thread.currentThread().name

inline fun doIf(condition: Boolean?, action: () -> Unit) {
    if (condition == true) action()
}

inline fun doIf(condition: () -> Boolean?, action: () -> Unit) {
    if (condition() == true ) action()
}

inline fun doIf(any: Any?, action: () -> Unit) {
    if (any != null ) action()
}
