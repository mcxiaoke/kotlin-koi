package com.mcxiaoke.koi.ext

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.view.View
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/22
 * Time: 13:14
 */


fun Activity.getActivity(): Activity = this

fun View.getActivity(): Activity = context as Activity

fun Fragment.finish() = activity?.finish()

fun SupportFragment.finish() = activity?.finish()

fun Activity.restart() {
    val intent = this.intent
    this.overridePendingTransition(0, 0)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    this.finish()
    this.overridePendingTransition(0, 0)
    this.startActivity(intent)
}


