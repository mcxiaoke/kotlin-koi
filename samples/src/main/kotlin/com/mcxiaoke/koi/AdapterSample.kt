package com.mcxiaoke.koi

import android.app.Activity
import android.os.Bundle
import com.mcxiaoke.koi.ext.quickAdapterOf
import com.mcxiaoke.koi.samples.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 23:26
 */

class QuickAdapterSample : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView.adapter = quickAdapterOf(
                android.R.layout.simple_list_item_2,
                (1..100).map { "List Item No.$it" })
        { binder, data ->
            binder.setText(android.R.id.text1, data)
            binder.setText(android.R.id.text2, "Index: ${binder.position}")
        }
    }
}
