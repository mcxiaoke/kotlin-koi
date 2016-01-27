package com.mcxiaoke.koi.samples

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_main.textView

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 13:15
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        textView.text = "Koi Samples"
    }
}
