package com.mcxiaoke.koi.core

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.mcxiaoke.koi.utils.newCachedThreadPool
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:07
 */
object CoreExecutor {
    private var thread: HandlerThread? = null


    fun quitHandlerThread() {
        thread?.quit()
    }

    val mainHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    val asyncHandler: Handler by lazy {
        thread = HandlerThread("global")
        thread?.start()
        Handler(thread?.looper)
    }

    val executor: ExecutorService by lazy {
        newCachedThreadPool("koi-core")
    }

    fun execute(task: () -> Unit): Future<Unit> {
        return executor.submit<Unit> { task() }
    }

    fun <T> submit(task: () -> T): Future<T> {
        return executor.submit(task)
    }
}

val mainHandler = CoreExecutor.mainHandler
val asyncHandler = CoreExecutor.asyncHandler
val asyncExecutor = CoreExecutor.executor


fun threadName(): String = Thread.currentThread().name

fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

fun mainThread(action: () -> Unit): Boolean = mainHandler.post(action)

fun mainThreadDelay(delayMillis: Long = 0, action: () -> Unit): Boolean
        = mainHandler.postDelayed(action, delayMillis)


inline fun doIf(condition: Boolean?, action: () -> Unit) {
    if (condition == true) action()
}

inline fun doIf(condition: () -> Boolean?, action: () -> Unit) {
    if (condition() == true ) action()
}

inline fun doIf(any: Any?, action: () -> Unit) {
    if (any != null ) action()
}
