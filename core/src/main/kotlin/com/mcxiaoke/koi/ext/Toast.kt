package com.mcxiaoke.koi.ext

import android.app.Fragment
import android.content.Context
import android.view.View
import android.widget.Toast
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 11:28
 */


fun Context.toast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

fun Context.toast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}

fun Context.longToast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun View.toast(resId: Int) = context.toast(resId)

fun View.toast(text: CharSequence) = context.toast(text)

fun View.longToast(resId: Int) = context.longToast(resId)

fun View.longToast(text: CharSequence) = context.longToast(text)

fun Fragment.toast(resId: Int) = activity.toast(resId)

fun Fragment.toast(text: CharSequence) = activity.toast(text)

fun Fragment.longToast(resId: Int) = activity.longToast(resId)

fun Fragment.longToast(text: CharSequence) = activity.longToast(text)

fun SupportFragment.toast(resId: Int) = activity.toast(resId)

fun SupportFragment.toast(text: CharSequence) = activity.toast(text)

fun SupportFragment.longToast(resId: Int) = activity.longToast(resId)

fun SupportFragment.longToast(text: CharSequence) = activity.longToast(text)