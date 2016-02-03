package com.mcxiaoke.koi.samples

import android.app.Activity
import android.app.Fragment
import com.mcxiaoke.koi.async.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/3 8:44
 */
class AsyncCoreSample {

    // available in any where
    fun executorFunctions() {
        // global main handler
        val uiHandler1 = CoreExecutor.mainHandler
        // or using this function
        val uiHandler2 = koiHandler()

        // global executor service
        val executor = CoreExecutor.executor
        // or using this function
        val executor2 = koiExecutor()

        // create thread pool functions
        val pool1 = newCachedThreadPool("cached")
        val pool2 = newFixedThreadPool("fixed", 4)
        val pool3 = newSingleThreadExecutor("single")

    }

    // available in any where
    fun mainThreadFunctions() {
        //check current thread
        // call from any where
        val isMain = isMainThread()

        // execute in main thread
        mainThread {
            print("${(1..8).asSequence().joinToString()}")
        }

        // delay execute in main thread
        mainThreadDelay(3000) {
            print("execute after 3000 ms")
        }

    }

    // available in any where
    fun safeFunctions() {
        val context = this
        // check Activity/Fragment lifecycle
        val alive = isContextAlive(context)

        // isContextAlive function impl
        fun <T> isContextAlive(context: T?): Boolean {
            return when (context) {
                null -> false
                is Activity -> !context.isFinishing
                is Fragment -> context.isAdded
                is android.support.v4.app.Fragment -> context.isAdded
                is Detachable -> !context.isDetached()
                else -> true
            }
        }

        fun func1() {
            print("func1")
        }
        // convert to safe function with context check
        // internal using  isContextAlive
        val safeFun1 = safeFunction(::func1)

        // call function with context check
        // internal using isContextAlive
        safeExecute(::func1)

        // direct use
        safeExecute { print("func1") }
    }
}
