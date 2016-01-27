package com.mcxiaoke.koi.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 11:26
 */


inline fun <reified T : Context> Context.getIntent(): Intent =
        Intent(this, T::class.java)

inline fun <reified T : Context> Context.getIntent(flags: Int): Intent {
    val intent = getIntent<T>()
    intent.setFlags(flags)
    return intent
}

inline fun <reified T : Context> Context.getIntent(extras: Bundle): Intent =
        getIntent<T>(0, extras)

inline fun <reified T : Context> Context.getIntent(flags: Int, extras: Bundle): Intent {
    val intent = getIntent<T>(flags)
    intent.putExtras(extras)
    return intent
}

inline fun <reified T : Activity> Activity.startActivity(): Unit =
        this.startActivity(getIntent<T>())

inline fun <reified T : Activity> Activity.startActivity(flags: Int): Unit =
        this.startActivity(getIntent<T>(flags))

inline fun <reified T : Activity> Activity.startActivity(extras: Bundle): Unit =
        this.startActivity(getIntent<T>(extras))

inline fun <reified T : Activity> Activity.startActivity(flags: Int, extras: Bundle): Unit =
        this.startActivity(getIntent<T>(flags, extras))

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int): Unit =
        this.startActivityForResult(getIntent<T>(), requestCode)

inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int,
                                                                  flags: Int): Unit =
        this.startActivityForResult(getIntent<T>(flags), requestCode)

inline fun <reified T : Activity> Activity.startActivityForResult(
        extras: Bundle, requestCode: Int): Unit =
        this.startActivityForResult(getIntent<T>(extras), requestCode)

inline fun <reified T : Activity> Activity.startActivityForResult(
        extras: Bundle, requestCode: Int, flags: Int): Unit =
        this.startActivityForResult(getIntent<T>(flags, extras), requestCode)
