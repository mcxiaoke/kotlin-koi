package com.mcxiaoke.koi.tests

import android.os.Looper
import com.mcxiaoke.koi.core.*
import com.mcxiaoke.koi.log.logv

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
            logv("mainHandler Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
        }
        logv("handler looper:${asyncHandler.looper}")
        logv("main looper:${Looper.getMainLooper()}")
        mainHandler.post {
            logv("mainHandler.post Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
        }
        val now = System.currentTimeMillis()
        mainHandler.postDelayed({
            logv("mainHandler.postDelayed Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
            isTrue((System.currentTimeMillis() - now) > 2500)
        }, 3000)

    }

    fun testAsync() {
        async2 {
            logv("async Thread:${Thread.currentThread()}")
            isFalse(isMainThread())
        }
        async2({
            logv("async action Thread:${Thread.currentThread()}")
            isFalse(isMainThread())
            Thread.sleep(2000)
        }, {
            logv("async callback Thread:${Thread.currentThread()}")
            isTrue(isMainThread())
        })
    }

    fun testCondition() {
        doIf(true, { logv("true") })
        doIf(false, { logv("false") })
        doIf(null, { logv("null") })

        doIf({ true }, { logv("{true}") })
        doIf({ false }, { logv("{false}") })
        doIf({ null }, { logv("{null}") })

        val a: String? = null
        val b: String? = "Hello"
        val c: Int = 100
        val d: Long? = 100
        val e = null
        val f = false

        doIf(a, { logv("a") })
        doIf(b, { logv("b") })
        doIf(c, { logv("c") })
        doIf(d, { logv("d") })
        doIf(e, { logv("e") })
        doIf(f, { logv("f") })

    }
}


