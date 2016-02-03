package com.mcxiaoke.koi.samples

import com.mcxiaoke.koi.async.*
import org.jetbrains.anko.async

/**
 * Author: mcxiaoke
 * Date:  2016/2/3 9:01
 */
class AsyncFunctionsSample {
    private val intVal = 1000
    private var strVal: String? = null

    // async functions with context check
    // internal using isContextAlive
    // context alive:
    // !Activity.isFinishing
    // Fragment.isAdded
    // !Detachable.isDetached
    //
    // available in any where
    // using in Activity/Fragment better
    fun asyncSafeFunction1() {
        // safe means context alive check
        // async
        asyncSafe {
            print("action executed only if context alive ")
            // if you want get caller context
            // maybe null
            val ctx = getCtx()
            // you can also using outside variables
            // not recommended
            // if context is Activity or Fragment
            // may cause memory leak
            print("outside value, $intVal $strVal")

            // you can using mainThreadSafe here
            // like a callback
            mainThreadSafe {
                // also with context alive check
                // if context dead, not executed
                print("code here executed in main thread")
            }
            // if you don't want context check, using mainThread{}
            mainThread {
                // no context check
                print("code here executed in main thread")
            }
        }
        // if your result or error is nullable
        // using asyncSafe2, just as asyncSafe
        // but type of result and error is T?, Throwable?
    }

    fun asyncSafeFunction2() {

        // async with callback
        asyncSafe(
                {
                    print("action executed in async thread")
                    listOf<Int>(1, 2, 3, 4, 5)
                },
                { result, error ->
                    // in main thread
                    print("callback executed in main thread")
                })
    }

    fun asyncSafeFunction3() {
        // async with success/failure callback
        asyncSafe(
                {
                    print("action executed in async thread")
                    "this string is result of the action"
                    // throw RuntimeException("action error")
                },
                { result ->
                    // if action success with no exception
                    print("success callback in main thread result:$result")
                },
                { error ->
                    // if action failed with exception
                    print("failure callback in main thread, error:$error")
                })
    }


    // if you don't want context check
    // using asyncUnsafe series functions
    // just replace asyncSafe with asyncUnsafe
    fun asyncUnsafeFunctions() {
        // async
        asyncUnsafe {
            print("action executed with no context check ")
            // may cause memory leak
            print("outside value, $intVal $strVal")

            mainThread {
                // no context check
                print("code here executed in main thread")
            }
        }
    }

    // async functions with delay
    // with context check
    // if context died, not executed
    // others just like asyncSafe
    fun asyncDelayFunctions() {
        // usage see asyncSafe
        asyncDelay(5000) {
            print("action executed after 5000ms only if context alive ")

            // you can using mainThreadSafe here
            // like a callback
            mainThreadSafe {
                // also with context alive check
                // if context dead, not executed
                print("code here executed in main thread")
            }
            // if you don't want context check, using mainThread{}
            mainThread {
                // no context check
                print("code here executed in main thread")
            }
        }
    }


}
