package com.mcxiaoke.koi.samples

import android.os.Bundle
import kotlinx.android.synthetic.main.act_splash.*

/**
 * User: mcxiaoke
 * Date: 15/11/5
 * Time: 15:43
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash)
        label.text = "Hello, World!"
    }
}
