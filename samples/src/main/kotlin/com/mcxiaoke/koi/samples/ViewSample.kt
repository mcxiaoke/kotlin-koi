package com.mcxiaoke.koi.samples

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.mcxiaoke.koi.ext.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 23:06
 */

class ViewExtensionSample(ctx: Context) : View(ctx) {


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