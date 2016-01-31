package com.mcxiaoke.koi.samples

import com.mcxiaoke.koi.KoiConfig
import com.mcxiaoke.koi.app.KoiApp
import kotlin.properties.Delegates

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 16:18
 */
class MainApp : KoiApp() {

    companion object {
        var app: MainApp by Delegates.notNull<MainApp>()
    }

    var splashShowed = true

    override fun onCreate() {
        super.onCreate()
        app = this
        KoiConfig.logEnabled = true
    }
}
