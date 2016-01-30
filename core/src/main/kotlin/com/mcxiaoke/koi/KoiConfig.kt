package com.mcxiaoke.koi

import android.util.Log

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 17:22
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
