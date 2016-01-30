package com.mcxiaoke.koi.ext

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 11:22
 */
inline fun Notification.build(context: Context, func: NotificationCompat.Builder.() -> Unit): Notification {
    val builder = NotificationCompat.Builder(context)
    builder.func()
    return builder.build()
}
