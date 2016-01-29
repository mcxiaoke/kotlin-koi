package com.mcxiaoke.koi.samples

import android.app.Application
import android.util.Log
import com.mcxiaoke.koi.log.KoiLogger
import kotlin.properties.Delegates

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 16:18
 */
class MainApp : Application() {

    companion object {
        var app: MainApp by Delegates.notNull<MainApp>()
    }

    var splashShowed = true

    override fun onCreate() {
        super.onCreate()
        KoiLogger.LOG_LEVEL = Log.VERBOSE
        app = this
    }
}
