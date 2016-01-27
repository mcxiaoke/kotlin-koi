package com.mcxiaoke.koi.tests

import android.os.Looper
import com.mcxiaoke.koi.core.*
import com.mcxiaoke.koi.log.lv

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 17:20
 */

class CoreTests {
    fun runAllTests() {
        testHandler()
        testAsync()
        testCondition()
    }

    fun testHandler() {
        mainHandler.post {
            lv("mainHandler Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
        }
        lv("handler looper:${asyncHandler.looper}")
        lv("main looper:${Looper.getMainLooper()}")
        mainHandler.post {
            lv("mainHandler.post Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
        }
        val now = System.currentTimeMillis()
        mainHandler.postDelayed({
            lv("mainHandler.postDelayed Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
            isTrue((System.currentTimeMillis() - now) > 2500)
        }, 3000)

    }

    fun testAsync() {
        async() {
            lv("async Thread:${Thread.currentThread()}")
            isFalse(isMainThread())
        }
        async({
            lv("async action Thread:${Thread.currentThread()}")
            isFalse(isMainThread())
            Thread.sleep(2000)
        }, {
            lv("async callback Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
        })
    }

    fun testCondition() {
        doIf(true, { lv("true") })
        doIf(false, { lv("false") })
        doIf(null, { lv("null") })

        doIf({ true }, { lv("{true}") })
        doIf({ false }, { lv("{false}") })
        doIf({ null }, { lv("{null}") })

        val a: String? = null
        val b: String? = "Hello"
        val c: Int = 100
        val d: Long? = 100
        val e = null
        val f = false

        doIf(a, { lv("a") })
        doIf(b, { lv("b") })
        doIf(c, { lv("c") })
        doIf(d, { lv("d") })
        doIf(e, { lv("e") })
        doIf(f, { lv("f") })

    }
}


