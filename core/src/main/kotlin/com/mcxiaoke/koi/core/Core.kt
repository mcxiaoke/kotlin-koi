package com.mcxiaoke.koi.core

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:07
 */

val mainHandler: Handler by lazy {
    Handler(Looper.getMainLooper())
}

val handler: Handler by lazy {
    val t: HandlerThread = HandlerThread("async")
    t.start()
    Handler(t.looper)
}

val executor: ExecutorService by lazy {
    Executors.newCachedThreadPool()
}

fun <T> T?.or(default: T): T = if (this == null) default else this
fun <T> T?.or(compute: () -> T): T = if (this == null) compute() else this

inline fun <T> callable(crossinline action: () -> T?): Callable<out T> {
    return Callable<T> { action() }
}

inline fun runnable(crossinline action: () -> Unit): Runnable {
    return Runnable { action() }
}

fun runOnMainThread(action: () -> Unit) {
    mainHandler.post(action)
}

fun runOnMainThreadDelay(action: () -> Unit, delayInMillis: Long) {
    mainHandler.postDelayed(action, delayInMillis)
}

fun async(action: () -> Unit, executor: ExecutorService): Future<out Any?> {
    return executor.submit(action)
}

fun async(action: () -> Unit): Unit = executor.execute(action)

inline fun <T> async(crossinline action: () -> T?,
                     crossinline callback: (result: T?) -> Unit): Unit {
    executor.submit {
        val ret: T? = action()
        mainHandler.post {
            callback(ret)
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
    if (any == true ) action()
}
