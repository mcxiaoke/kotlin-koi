package com.mcxiaoke.koi.samples.app

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcxiaoke.koi.async.*
import com.mcxiaoke.koi.ext.quickAdapterOf
import com.mcxiaoke.koi.log.logd
import com.mcxiaoke.koi.log.logv
import com.mcxiaoke.koi.samples.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.lang.ref.WeakReference
import java.util.concurrent.Executors

/**
 * User: mcxiaoke
 * Date: 15/11/5
 * Time: 15:43
 */
class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logv("onCreate()")
        setContentView(R.layout.activity_main)
        listView.adapter = quickAdapterOf(
                android.R.layout.simple_list_item_2,
                (1..100).map { "List Item No.$it" })
        { binder, data ->
            binder.setText(android.R.id.text1, data)
            binder.setText(android.R.id.text2, "Index: ${binder.position}")
        }
        logv("onCreate() ${listView.adapter}")
        testSafe()
        testAsync()
        testInline()
    }

    fun createAction(name: String): () -> Unit {
        return { logv("createAction() name:$name") }
    }

    fun testInline() {
        var weakCtx = WeakContext<Context>(WeakReference(this))
        weakCtx.safe {
            listOf(1, 2, 3, 4, 5)
        }
    }

    fun testSafe() {
        asyncUnsafe {
            logv("testSafe() async action start")
            Thread.sleep(5000)
            logv("testSafe() async action end")
            safeFunction(createAction("safeFunc1"))()
            safeExecute(createAction("safe1"))
            mainThread {
                safeExecute(createAction("safe2"))
                safeFunction(createAction("safeFunc2"))()
            }
            mainThread {
                logv("testSafe() mainThreadSafe")
            }
        }
    }

    fun testAsync() {
        async() {
            logv("async action")
            Thread.sleep(3000)
            uiThread { logv("async callback  $it") }
        }
        asyncSafe {
            logv("asyncSafe1 action")
            Thread.sleep(3000)
            mainThreadSafe { logv("asyncSafe1 callback  $it") }
        }
        val pool = newCachedThreadPool("cat")
        asyncSafe(pool) {
            logv("asyncSafe2 action")
            Thread.sleep(3000)
            mainThreadSafe {
                logv("asyncSafe2 callback $it")
            }
        }
        asyncSafe {
            logv("asyncSafe3 action")
            Thread.sleep(3000)
            mainThreadSafe {
                logv("asyncSafe3 callback  $it")
            }
        }
        // FIXME
        // 1.0.0-beta-4589
        // inline functions compile error
        // https://youtrack.jetbrains.com/issue/KT-10137
        asyncSafe(
                {
                    logd("asyncSafeAction1")
                    Thread.sleep(6000)
                    listOf(1, 2, 3, 4, 5)
                },
                { r, e ->
                    logd("asyncSafeCallback1:$r $e")
                }
        )
        asyncSafe(
                {
                    logd("asyncSafeAction2")
                    listOf(1, 2, 3, 4, 5)
                    Thread.sleep(6000)
                    throw RuntimeException("asyncSafe")
                },
                { r ->
                    logd("asyncSafeCallback2: success:$r")
                }
                ,
                { e ->
                    logd("asyncSafeCallback2: failure:$e")
                }
        )
        asyncSafe (
                Executors.newSingleThreadExecutor(),
                {
                    logd("asyncSafeAction3")
                    Thread.sleep(6000)
                    "asyncSafeAction3 Result"
                },
                { r, e ->
                    logd("asyncSafeCallback3:$r $e")
                }
        )
        logd("asyncDelay add actions")
        asyncDelay(5000) {
            logv("asyncDelay0 5000 action")
        }
        asyncDelay(5000) {
            logv("asyncDelay1 5000 action")
            Thread.sleep(5000)
            mainThreadSafe {
                logv("asyncDelay1 5000 callback")
            }
        }
        asyncDelay(5000) {
            logv("asyncDelay2 5000 action")
            Thread.sleep(5000)
            mainThread {
                logv("asyncDelay2 5000 callback")
            }
        }

        asyncUnsafe {
            logv("asyncUnsafe 1 action")
            Thread.sleep(4000)
            mainThread { logv("asyncUnsafe 1 callback") }
        }
        asyncUnsafe(pool) {
            logv("asyncUnsafe 2 action")
            Thread.sleep(4000)
            mainThread {
                logv("asyncUnsafe 2 callback")
            }
        }
        asyncUnsafe {
            logv("asyncUnsafe 3 action")
            Thread.sleep(4000)
            mainThread {
                logv("asyncUnsafe 3 callback")
            }
        }
        asyncUnsafe(
                {
                    logd("asyncUnsafe Action1")
                    listOf<Int>(1, 2, 3, 4, 5)
                },
                { r, e ->
                    logd("asyncUnsafe Callback1:$r $e")
                }
        )
        asyncUnsafe(
                {
                    logd("asyncUnsafe Action2")
                    listOf(1, 2, 3, 4, 5)
                    throw RuntimeException("asyncSafe")
                },
                { r ->
                    logd("asyncUnsafe Callback2: success:$r")
                }
                ,
                { e ->
                    logd("asyncUnsafe Callback2: failure:$e")
                }
        )
        asyncUnsafe (
                Executors.newSingleThreadExecutor(),
                {
                    logd("asyncUnsafe Action3")
                },
                { r, e ->
                    logd("asyncUnsafe Callback3:$r $e")
                }
        )
    }

}
