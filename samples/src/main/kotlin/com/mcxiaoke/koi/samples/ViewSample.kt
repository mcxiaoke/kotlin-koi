package com.mcxiaoke.koi.samples

import android.app.Activity
import android.content.Context
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.mcxiaoke.koi.ext.*
import org.jetbrains.anko.onKey
import org.jetbrains.anko.onTouch

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 23:06
 */

class ViewExtensionSample1 : Activity() {

    fun viewListeners1() {
        val view = View(this)
        // View.OnClickListener
        view.onClick { print("view clicked") }
        // View.OnLongClickListener
        view.onLongClick { print("view long clicked");false }
        // View.OnKeyListener
        view.onKeyEvent { view, keyCode, event ->
            print("keyEvent: action:${event.action} code:$keyCode")
            false
        }
        // View.OnTouchListener
        view.onTouchEvent { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> print("touch down")
                MotionEvent.ACTION_UP -> print("touch up")
                MotionEvent.ACTION_MOVE -> print("touch move")
            }
            false
        }
        // View.OnFocusChangeListener
        view.onFocusChange { view, hasFocus ->
            print("focus changed = $hasFocus")
        }
    }

    fun viewListeners2() {
        // TextWatcher
        val editText = EditText(this)
        editText.onTextChange { text, start, before, count ->
            print("text changed: $text")
        }

        // OnCheckedChangeListener
        val checkBox = CheckBox(this)
        checkBox.onCheckedChanged { button, isChecked ->
            print("CheckBox value changed:$isChecked")
        }

        // OnSeekBarChangeListener
        val seekBar = SeekBar(this)
        seekBar.onProgressChanged { seekBar, progress, fromUser ->
            print("seekBar progress: $progress")
        }
    }

    fun listViewListeners() {
        val listView = ListView(this)
        // OnItemClickListener
        listView.onItemClick { parent, view, position, id ->
            print("onItemClick: position=$position")
        }
        // OnScrollListener
        listView.onScrollChanged { view, scrollState ->
            print("scroll state changed")
        }
    }
}

class ViewExtensionSample2(ctx: Context) : View(ctx) {


    // available for View
    fun viewSample() {
        val w = dm.widthPixels
        val h = dm.heightPixels

        val v1 = 32.5f
        val dp1 = v1.pxToDp()

        val v2 = 24f
        val px1 = v2.dpToPx()

        val dp2 = pxToDp(56)
        val px2 = dpToPx(32)

        val dp3 = 72.pxToDp()
        val px3 = 48.dpToPx()

        hideSoftKeyboard()

        val editText = EditText(context)
        editText.showSoftKeyboard()
        editText.toggleSoftKeyboard()
    }

}