package com.mcxiaoke.koi.async

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 14:20
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
        thread = HandlerThread("koi-global")
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

fun koiExecutor(): ExecutorService = CoreExecutor.executor

fun threadName(): String = Thread.currentThread().name


