package com.mcxiaoke.koi.ext

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.app.Service
import android.view.View
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 10:30
 */

val Activity.app: Application
    get() = application

val Activity.activity: Activity
    get() = this

val Service.app: Application
    get() = application

val View.app: Application
    get() = context.applicationContext as Application

val Fragment.app: Application
    get() = activity.application

fun Fragment.finish() {
    activity?.finish()
}

val SupportFragment.app: Application
    get() = activity.application

fun SupportFragment.finish() {
    activity?.finish()
}
