package com.mcxiaoke.koi.samples

import android.os.Bundle
import android.support.v4.app.Fragment
import com.mcxiaoke.koi.async.asyncSafe
import com.mcxiaoke.koi.async.mainThreadSafe
import com.mcxiaoke.koi.log.logv

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 16:15
 */
class MainFragment : Fragment() {
    val flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun testAsync() {
        asyncSafe {
            logv("Debug", "hello, world 1")

            mainThreadSafe {
                logv("Debug", "hello, world 2")
            }
        }
    }
}
