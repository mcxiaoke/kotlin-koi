package com.mcxiaoke.koi.core

import android.app.Fragment
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.mcxiaoke.koi.utils.newCachedThreadPool
import java.lang.ref.WeakReference
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
        newCachedThreadPool("core")
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

fun isMainThread(): Boolean {
    return Looper.myLooper() == Looper.getMainLooper()
}

fun mainThread(action: () -> Unit): Boolean = mainHandler.post(action)

fun mainThreadDelay(delayMillis: Long = 0, action: () -> Unit): Boolean
        = mainHandler.postDelayed(action, delayMillis)

fun async2(action: () -> Unit, executor: ExecutorService): Future<Unit> {
    return CoreExecutor.submit<Unit>(action)
}

fun async2(action: () -> Unit): Future<Unit> = CoreExecutor.submit<Unit>(action)

inline fun <T> async2(crossinline action: () -> T?,
                      crossinline callback: (result: T?) -> Unit): Future<Unit> {
    return CoreExecutor.submit<Unit> {
        val ret = action()
        mainThread {
            callback(ret)
        }
    }
}

inline fun <T> async2(crossinline action: () -> T?,
                      crossinline success: (result: T?) -> Unit,
                      crossinline failure: (error: Throwable) -> Unit): Future<*> {
    return asyncExecutor.submit {
        try {
            val ret = action()
            mainThread {
                success(ret)
            }
        } catch(e: Exception) {
            mainThread {
                failure(e)
            }
        }
    }
}


inline fun doIf(condition: Boolean?, action: () -> Unit) {
    if (condition == true) action()
}

inline fun doIf(condition: () -> Boolean?, action: () -> Unit) {
    if (condition() == true ) action()
}

inline fun doIf(any: Any?, action: () -> Unit) {
    if (any != null ) action()
}
