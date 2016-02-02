package com.mcxiaoke.koi.ext

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:38
 */


val View.dm: DisplayMetrics
    get() = resources.displayMetrics


fun Float.pxToDp(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val dp = this / (metrics.densityDpi / 160f)
    return Math.round(dp)
}

fun Float.dpToPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val px = this * (metrics.densityDpi / 160f)
    return Math.round(px)
}

fun Int.pxToDp(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val dp = this / (metrics.densityDpi / 160f)
    return Math.round(dp)
}

fun Int.dpToPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val px = this * (metrics.densityDpi / 160f)
    return Math.round(px)
}

fun View.dpToPx(dp: Int): Int {
    return (dp * this.dm.density + 0.5).toInt()
}

fun View.pxToDp(px: Int): Int {
    return (px / this.dm.density + 0.5).toInt()
}

fun View.hideSoftKeyboard() {
    context.getInputMethodManager().hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.showSoftKeyboard() {
    if (this.requestFocus()) {
        context.getInputMethodManager().showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun EditText.toggleSoftKeyboard() {
    if (this.requestFocus()) {
        context.getInputMethodManager().toggleSoftInput(0, 0)
    }
}