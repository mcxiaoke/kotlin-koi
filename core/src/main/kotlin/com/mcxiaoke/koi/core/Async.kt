package com.mcxiaoke.koi.core

import android.app.Activity
import android.app.Fragment
import android.content.Context
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import android.support.v4.app.Fragment as SupportFragment

/**
 * Author: mcxiaoke
 * Date:  2016/1/29 8:23
 */
interface Detachable {
    val isDetached: Boolean
}

class WeakContext<T>(val weakRef: WeakReference<T>) {

    val <T : Any> WeakContext<T>.hasValidContext: Boolean
        get() {
            val context = weakRef.get() ?: return false
            return when (context) {
                is Activity -> !context.isFinishing
                is Fragment -> context.isAdded
                is SupportFragment -> context.isAdded
                is Detachable -> !context.isDetached
                else -> true
            }
        }
}

//*******************************************************************//
//    Execute Task on Main Thread
//*******************************************************************//

fun Context.mainThread(action: Context.() -> Unit) {
    if (isMainThread()) action() else CoreExecutor.mainHandler.post { action() }
}

inline fun Fragment.mainThread(crossinline action: () -> Unit) {
    activity?.mainThread { action() }
}

inline fun SupportFragment.mainThread(crossinline action: () -> Unit) {
    activity?.mainThread { action() }
}

inline fun <T> WeakContext<T>.mainThread(crossinline action: (T) -> Unit) {
    val ref = weakRef.get() ?: return
    if (isMainThread()) {
        action(ref)
    } else {
        CoreExecutor.mainHandler.post { action(ref) }
    }
}

inline fun <T : Any> WeakContext<T>.mainThreadSafe(
        crossinline action: (T) -> Unit) {
    val context = weakRef.get() ?: return
    // may using this.contextAlive
    when (context) {
        is Activity -> if (context.isFinishing) return
        is Fragment -> if (!context.isAdded) return
        is SupportFragment -> if (!context.isAdded) return
        is Detachable -> if (context.isDetached) return
    }
    mainThread { action(context) }
}

//*******************************************************************//
//    Async Functions Simple
//*******************************************************************//

fun <T : Any> async2(executor: ExecutorService, action: () -> T): Future<T>
        = executor.submit<T>(action)

fun <T : Any> async2(action: () -> T): Future<T>
        = CoreExecutor.submit<T>(action)

inline fun <T : Any> async2(
        crossinline action: () -> T?,
        crossinline callback: (result: T?) -> Unit): Future<Unit> {
    return async2(CoreExecutor.executor, action, callback)
}

inline fun <T : Any> async2(
        executor: ExecutorService,
        crossinline action: () -> T?,
        crossinline callback: (result: T?) -> Unit): Future<Unit> {
    return executor.submit<Unit> {
        val ret = action()
        mainThread { callback(ret) }
    }
}

inline fun <T : Any> async2(
        crossinline action: () -> T?,
        crossinline success: (result: T?) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    return async2(CoreExecutor.executor, action, success, failure)
}

inline fun <T : Any> async2(
        executor: ExecutorService,
        crossinline action: () -> T?,
        crossinline success: (result: T?) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    return executor.submit<Unit> {
        try {
            val ret = action()
            mainThread { success(ret) }
        } catch(e: Exception) {
            mainThread { failure(e) }
        }
    }
}

//*******************************************************************//
//    Async Functions With Delay
//*******************************************************************//

fun <T : Any> asyncDelay(delayInMillis: Long, action: () -> T): Unit {
    asyncDelay(CoreExecutor.executor, delayInMillis, action)
}

fun <T : Any> asyncDelay(executor: ExecutorService, delayInMillis: Long, action: () -> T): Unit {
    CoreExecutor.asyncHandler.postDelayed({ executor.submit<T>(action) }, delayInMillis)
}

inline fun <T : Any> asyncDelay(
        delayInMillis: Long,
        crossinline action: () -> T?,
        crossinline callback: (result: T?) -> Unit): Unit {
    return asyncDelay(CoreExecutor.executor, delayInMillis, action, callback)
}

inline fun <T : Any> asyncDelay(
        executor: ExecutorService,
        delayInMillis: Long,
        crossinline action: () -> T?,
        crossinline callback: (result: T?) -> Unit): Unit {
    val runnable = {
        executor.submit<Unit> {
            val ret = action()
            mainThread { callback(ret) }
        }
        Unit
    }
    CoreExecutor.asyncHandler.postDelayed(runnable, delayInMillis)
}

inline fun <T : Any> asyncDelay(
        delayInMillis: Long,
        crossinline action: () -> T?,
        crossinline success: (result: T?) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Unit {
    return asyncDelay(CoreExecutor.executor, delayInMillis, action, success, failure)
}

inline fun <T : Any> asyncDelay(
        executor: ExecutorService,
        delayInMillis: Long,
        crossinline action: () -> T?,
        crossinline success: (result: T?) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Unit {
    val runnable = {
        executor.submit<Unit> {
            try {
                val ret = action()
                mainThread { success(ret) }
            } catch(e: Exception) {
                mainThread { failure(e) }
            }
        }
        Unit
    }
    CoreExecutor.asyncHandler.postDelayed(runnable, delayInMillis)
}

//*******************************************************************//
//    Async Functions With Context Check
//*******************************************************************//


inline fun <T : Any, R : Any> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R): Future<R> {
    return asyncSafe(CoreExecutor.executor, action)
}

inline fun <T : Any, R : Any> T.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R): Future<R> {
    val context = WeakContext(WeakReference(this))
    return executor.submit<R> { context.action() }
}

inline fun <T : Any, R : Any> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit>? {
    return asyncSafe(CoreExecutor.executor, action, callback)
}

inline fun <T : Any, R : Any> T.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit>? {
    val context = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        try {
            val ret = context.action()
            context.mainThreadSafe { callback(ret, null) }
        } catch(ex: Exception) {
            context.mainThreadSafe { callback(null, ex) }
        }
    }
}

inline fun <T : Any, R : Any> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    return asyncSafe(CoreExecutor.executor, action, success, failure)
}

inline fun <T : Any, R : Any> T.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    val context = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        try {
            val ret = context.action()
            context.mainThreadSafe { success(ret) }
        } catch(ex: Exception) {
            context.mainThreadSafe { failure(ex) }
        }
    }
}

//*******************************************************************//
//    Async Nullable Functions With Context Check
//*******************************************************************//

inline fun <T : Any, R : Any> T.asyncNullSafe(
        crossinline action: WeakContext<T>.() -> R?,
        noinline callback: ((result: R?, error: Throwable?) -> Unit)?): Future<Unit>? {
    return asyncNullSafe(CoreExecutor.executor, action, callback)
}

inline fun <T : Any, R : Any> T.asyncNullSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R?,
        noinline callback: ((result: R?, error: Throwable?) -> Unit)?): Future<Unit>? {
    val context = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        try {
            val ret = context.action()
            context.mainThreadSafe { callback?.invoke(ret, null) }
        } catch(ex: Exception) {
            context.mainThreadSafe { callback?.invoke(null, ex) }
        }
    }
}

inline fun <T : Any, R : Any> T.asyncNullSafe(
        crossinline action: WeakContext<T>.() -> R?,
        noinline success: ((result: R?) -> Unit)?,
        noinline failure: ((error: Throwable?) -> Unit)?): Future<Unit> {
    return asyncNullSafe(CoreExecutor.executor, action, success, failure)
}

inline fun <T : Any, R : Any> T.asyncNullSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R?,
        noinline success: ((result: R?) -> Unit)?,
        noinline failure: ((error: Throwable?) -> Unit)?): Future<Unit> {
    val context = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        try {
            val ret = context.action()
            context.mainThreadSafe { success?.invoke(ret) }
        } catch(e: Exception) {
            context.mainThreadSafe { failure?.invoke(e) }
        }
    }
}



