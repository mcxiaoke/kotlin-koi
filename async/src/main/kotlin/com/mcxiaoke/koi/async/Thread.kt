package com.mcxiaoke.koi.async

import java.util.concurrent.*

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 11:05
 */

class CounterThreadFactory(name: String?) : ThreadFactory {
    private var count: Int = 0
    private val name: String = name ?: "koi"

    override fun newThread(r: Runnable): Thread {
        val thread = Thread(r)
        thread.name = name + "-thread-" + count++
        return thread
    }
}

fun newCachedThreadPool(name: String): ThreadPoolExecutor {
    return ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            SynchronousQueue<Runnable>(),
            CounterThreadFactory(name))
}

fun newFixedThreadPool(name: String, nThreads: Int): ThreadPoolExecutor {
    return ThreadPoolExecutor(nThreads, nThreads,
            0L, TimeUnit.MILLISECONDS,
            LinkedBlockingQueue<Runnable>(),
            CounterThreadFactory(name))
}

fun newSingleThreadExecutor(name: String): ThreadPoolExecutor {
    return newFixedThreadPool(name, 1)
}
