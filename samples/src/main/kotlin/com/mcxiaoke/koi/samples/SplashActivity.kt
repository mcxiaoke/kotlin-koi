package com.mcxiaoke.koi.samples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcxiaoke.koi.ext.startActivity

/**
 * User: mcxiaoke
 * Date: 15/11/5
 * Time: 15:43
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMain()
    }

    private fun showMain() {
        startActivity<MainActivity>()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
