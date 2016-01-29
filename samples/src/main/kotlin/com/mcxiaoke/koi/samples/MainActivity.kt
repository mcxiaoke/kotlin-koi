package com.mcxiaoke.koi.samples

import android.os.Bundle
import com.mcxiaoke.koi.core.asyncSafe
import com.mcxiaoke.koi.core.mainThreadSafe
import com.mcxiaoke.koi.log.logd
import com.mcxiaoke.koi.log.logv
import com.mcxiaoke.koi.utils.newCachedThreadPool
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import org.jetbrains.anko.async as ankoAsync
import org.jetbrains.anko.uiThread as ankoUiThread

/**
 * User: mcxiaoke
 * Date: 15/11/5
 * Time: 15:43
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logv("onCreate()")
        setContentView(R.layout.activity_main)
        iconView.setImageResource(R.drawable.ic_launcher)
        testAsync()
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
                    listOf<Int>(1, 2, 3, 4, 5)
                },
                { r, e ->
                    logd("asyncSafeCallback1:$r $e")
                }
        )
        asyncSafe(
                {
                    logd("asyncSafeAction2")
                    listOf<Int>(1, 2, 3, 4, 5)
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
