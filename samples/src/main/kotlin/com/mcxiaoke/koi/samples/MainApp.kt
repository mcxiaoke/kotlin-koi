package com.mcxiaoke.koi.samples

import android.app.Application
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

    var splashShowed = false

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}
