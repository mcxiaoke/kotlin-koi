package com.mcxiaoke.koi.samples

import android.os.Bundle
import com.mcxiaoke.koi.log.lv
import kotlinx.android.synthetic.main.activity_main.*

/**
 * User: mcxiaoke
 * Date: 15/11/5
 * Time: 15:43
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lv("onCreate()")
        setContentView(R.layout.activity_main)
        iconView.setImageResource(R.drawable.ic_launcher)
    }

}
