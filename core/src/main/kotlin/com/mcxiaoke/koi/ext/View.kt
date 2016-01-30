package com.mcxiaoke.koi.ext

import android.app.Activity
import android.app.Fragment
import android.content.Context
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

inline fun <reified T : View> View.find(id: Int): T = this.findViewById(id) as T

inline fun <reified T : View> Activity.find(id: Int): T = this.findViewById(id) as T

inline fun <reified T : View> Fragment.find(id: Int): T = this.view.findViewById(id) as T

inline fun <reified T : View> SupportFragment.find(id: Int): T = this.view.findViewById(id) as T


fun Context.dpToPx(dp: Int): Int {
    return (dp * this.resources.displayMetrics.density + 0.5).toInt()
}

fun Context.pxToDp(px: Int): Int {
    return (px / this.resources.displayMetrics.density + 0.5).toInt()
}

fun View.dpToPx(dp: Int): Int {
    return (dp * this.resources.displayMetrics.density + 0.5).toInt()
}

fun View.pxToDp(px: Int): Int {
    return (px / this.resources.displayMetrics.density + 0.5).toInt()
}

fun Context.inflateLayout(layoutResId: Int): View =
        inflateView(this, layoutResId, null, false)

fun Context.inflateLayout(layoutResId: Int, parent: ViewGroup): View =
        inflateLayout(layoutResId, parent, true)

fun Context.inflateLayout(layoutResId: Int, parent: ViewGroup, attachToRoot: Boolean): View =
        inflateView(this, layoutResId, parent, attachToRoot)

private fun inflateView(context: Context, layoutResId: Int, parent: ViewGroup?,
                        attachToRoot: Boolean): View =
        LayoutInflater.from(context).inflate(layoutResId, parent, attachToRoot)

fun View.hideSoftKeyboard() {
    context.getInputMethodManager().hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.showSoftKeyboard() {
    if (this.requestFocus()) {
        context.getInputMethodManager().showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun EditText.toggleSoftInput() {
    if (this.requestFocus()) {
        context.getInputMethodManager().toggleSoftInput(0, 0)
    }
}