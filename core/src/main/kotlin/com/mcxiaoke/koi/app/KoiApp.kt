package com.mcxiaoke.koi.app

import android.app.Application
import com.mcxiaoke.koi.KoiConfig
import kotlin.properties.Delegates

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 10:32
 */

open class KoiApp : Application() {
    companion object {
        var app: KoiApp by Delegates.notNull<KoiApp>()
        var hot: Boolean = false
        val kv: MutableMap<String, Any> = hashMapOf()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        hot = true
        KoiConfig.logEnabled = true
    }
}