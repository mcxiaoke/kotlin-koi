package com.mcxiaoke.koi.samples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcxiaoke.koi.async.*
import com.mcxiaoke.koi.core.*
import com.mcxiaoke.koi.ext.quickAdapterOf
import com.mcxiaoke.koi.log.logd
import com.mcxiaoke.koi.log.logv
import com.mcxiaoke.koi.async.newCachedThreadPool
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import org.jetbrains.anko.async as ankoAsync
import org.jetbrains.anko.uiThread as ankoUiThread

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
    }

    fun createAction(name: String): () -> Unit {
        return { logv("createAction() name:$name") }
    }

    fun testSafe() {
        async2 {
            logv("testSafe() async action start")
            Thread.sleep(5000)
            logv("testSafe() async action end")
            safeFunc(createAction("safeFunc1"))()
            safe(createAction("safe1"))
            mainThread {
                safe(createAction("safe2"))
                safeFunc(createAction("safeFunc2"))()
            }
            mainThreadSafe {
                logv("testSafe() mainThreadSafe")
            }
        }
    }

    fun testAsync() {
        ankoAsync() {
            logv("async action")
            Thread.sleep(5000)
            ankoUiThread { logv("async callback") }
        }
        asyncSafe {
            logv("asyncSafe1 action")
            Thread.sleep(5000)
            mainThreadSafe { logv("asyncSafe1 callback") }
        }
        val pool = newCachedThreadPool("cat")
        asyncSafe(pool) {
            logv("asyncSafe2 action")
            Thread.sleep(5000)
            mainThreadSafe { logv("asyncSafe2 callback") }
        }
        asyncSafe(
                {
                    logd("asyncSafeAction1")
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
                },
                { r, e ->
                    logd("asyncSafeCallback3:$r $e")
                }
        )
    }

}
