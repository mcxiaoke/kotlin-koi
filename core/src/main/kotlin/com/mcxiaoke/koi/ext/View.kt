package com.mcxiaoke.koi.ext

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
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

fun View.onClick(f: (View) -> Unit) {
    this.setOnClickListener(f)
}

fun View.onLongClick(f: (View) -> Boolean) {
    this.setOnLongClickListener(f)
}

fun View.onTouchEvent(f: (View, MotionEvent) -> Boolean) {
    this.setOnTouchListener(f)
}

fun View.onKeyEvent(f: (View, Int, KeyEvent) -> Boolean) {
    this.setOnKeyListener(f)
}

fun View.onFocusChange(f: (View, Boolean) -> Unit) {
    this.setOnFocusChangeListener(f)
}

fun CompoundButton.onCheckedChanged(f: (CompoundButton, Boolean) -> Unit) {
    this.setOnCheckedChangeListener(f)
}

fun AdapterView<*>.onItemClick(f: (AdapterView<*>, View, Int, Long) -> Unit) {
    this.setOnItemClickListener(f)
}

inline fun <T : AbsListView> T.onScrollChanged(
        crossinline stateChanged: (View, Int) -> Unit) {
    val listener = object : AbsListView.OnScrollListener {
        override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
            stateChanged(view, scrollState)
        }

        override fun onScroll(view: AbsListView, firstVisibleItem: Int,
                              visibleItemCount: Int, totalItemCount: Int) {
        }
    }
    this.setOnScrollListener(listener)
}

inline fun EditText.onTextChange(crossinline f: (CharSequence, Int, Int, Int) -> Unit) {
    val listener = object : KoiTextWatcher() {
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            f(s, start, before, count)
        }
    }
    this.addTextChangedListener(listener)
}

fun SeekBar.onProgressChanged(f: (SeekBar, Int, Boolean) -> Unit) {
    val listener = object : KoiSeekBarChangeListener() {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            super.onProgressChanged(seekBar, progress, fromUser)
            f(seekBar, progress, fromUser)
        }
    }
    this.setOnSeekBarChangeListener(listener)
}

abstract class KoiTextWatcher : TextWatcher {
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable) {
    }
}

abstract class KoiSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
    override fun onStopTrackingTouch(seekBar: SeekBar) {
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
    }
}