package com.mcxiaoke.koi.samples

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
    }

    // easy way to create array adapter
    fun adapterFunctions() {
        listView.adapter = quickAdapterOf(
                android.R.layout.simple_list_item_2,
                (1..100).map { "List Item No.$it" })
        { binder, data ->
            binder.setText(android.R.id.text1, data)
            binder.setText(android.R.id.text2, "Index: ${binder.position}")
        }

        val adapter2 = quickAdapterOf<String>(android.R.layout.simple_list_item_1) {
            binder, data ->
            binder.setText(android.R.id.text1, data)
        }
        adapter2.addAll(listOf("Cat", "Dog", "Rabbit"))

        val adapter3 = quickAdapterOf<Int>(android.R.layout.simple_list_item_1,
                arrayOf(1, 2, 3, 4, 5, 6)) {
            binder, data ->
            binder.setText(android.R.id.text1, "Item Number: $data")
        }

        val adapter4 = quickAdapterOf<Int>(android.R.layout.simple_list_item_1,
                setOf(22, 33, 4, 5, 6, 8, 8, 8)) {
            binder, data ->
            binder.setText(android.R.id.text1, "Item Number: $data")
        }
    }
}
