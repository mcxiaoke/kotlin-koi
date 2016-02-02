package com.mcxiaoke.koi.ext

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 11:26
 */


inline fun <reified T : Context> Context.newIntent(): Intent =
        Intent(this, T::class.java)

inline fun <reified T : Context> Context.newIntent(flags: Int): Intent {
    val intent = newIntent<T>()
    intent.setFlags(flags)
    return intent
}

inline fun <reified T : Context> Context.newIntent(extras: Bundle): Intent =
        newIntent<T>(0, extras)

inline fun <reified T : Context> Context.newIntent(flags: Int, extras: Bundle): Intent {
    val intent = newIntent<T>(flags)
    intent.putExtras(extras)
    return intent
}

inline fun <reified T : Activity> Activity.startActivity(): Unit =
        this.startActivity(newIntent<T>())

inline fun <reified T : Activity> Activity.startActivity(flags: Int): Unit =
        this.startActivity(newIntent<T>(flags))

inline fun <reified T : Activity> Activity.startActivity(extras: Bundle): Unit =
        this.startActivity(newIntent<T>(extras))

inline fun <reified T : Activity> Activity.startActivity(flags: Int, extras: Bundle): Unit =
        this.startActivity(newIntent<T>(flags, extras))

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int): Unit =
        this.startActivityForResult(newIntent<T>(), requestCode)

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int,
                                                                  flags: Int): Unit =
        this.startActivityForResult(newIntent<T>(flags), requestCode)

inline fun <reified T : Activity> Activity.startActivityForResult(
        extras: Bundle, requestCode: Int): Unit =
        this.startActivityForResult(newIntent<T>(extras), requestCode)

inline fun <reified T : Activity> Activity.startActivityForResult(
        extras: Bundle, requestCode: Int, flags: Int): Unit =
        this.startActivityForResult(newIntent<T>(flags, extras), requestCode)

inline fun <reified T : Service> Context.startService(): ComponentName =
        this.startService(newIntent<T>())

inline fun <reified T : Service> Context.startService(flags: Int): ComponentName =
        this.startService(newIntent<T>(flags))

inline fun <reified T : Service> Context.startService(extras: Bundle): ComponentName =
        this.startService(newIntent<T>(extras))

inline fun <reified T : Service> Context.startService(extras: Bundle,
                                                      flags: Int): ComponentName
        = this.startService(newIntent<T>(flags, extras))
