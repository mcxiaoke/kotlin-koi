package com.mcxiaoke.koi.tests

import com.mcxiaoke.koi.assert.throwIfFalse
import com.mcxiaoke.koi.assert.throwIfTrue
import com.mcxiaoke.koi.async.asyncUnsafe
import com.mcxiaoke.koi.async.isMainThread
import com.mcxiaoke.koi.doIf
import com.mcxiaoke.koi.log.logv

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 17:20
 */

class CoreTests {
    companion object {
        const val tag = "CoreTests"
    }

    fun runAllTests() {
        testAsync()
        testCondition()
    }

    fun testAsync() {
        asyncUnsafe {
            logv(tag, "async Thread:${Thread.currentThread()}")
            throwIfTrue(isMainThread())
        }
        asyncUnsafe({
            throwIfTrue(isMainThread())
            logv(tag, "async action Thread:${Thread.currentThread()}")
            Thread.sleep(2000)
        }, { r, e ->
            logv(tag, "async callback Thread:${Thread.currentThread()}")
            throwIfFalse(isMainThread())
        })
    }

    fun testCondition() {
        doIf(true, { logv(tag, "true") })
        doIf(false, { logv(tag, "false") })
        doIf(null, { logv(tag, "null") })

        doIf({ true }, { logv(tag, "{true}") })
        doIf({ false }, { logv(tag, "{false}") })
        doIf({ null }, { logv(tag, "{null}") })

        val a: String? = null
        val b: String? = "Hello"
        val c: Int = 100
        val d: Long? = 100
        val e = null
        val f = false

        doIf(a, { logv(tag, "a") })
        doIf(b, { logv(tag, "b") })
        doIf(c, { logv(tag, "c") })
        doIf(d, { logv(tag, "d") })
        doIf(e, { logv(tag, "e") })
        doIf(f, { logv(tag, "f") })

    }
}


