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

class WeakContext<T>(val weakRef: WeakReference<T>) {

    val <T : Any> WeakContext<T>.contextAlive: Boolean
        get() {
            val context = weakRef.get() ?: return false
            return when (context) {
                is Activity -> !context.isFinishing
                is Fragment -> context.isAdded
                is SupportFragment -> context.isAdded
                else -> true
            }
        }
}

//*******************************************************************//
//    Execute Task on Main Thread
//*******************************************************************//

fun Context.mainThread(action: Context.() -> Unit) {
    if (isMainThread()) action() else mainHandler.post { action() }
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
        mainHandler.post { action(ref) }
    }
}

//*******************************************************************//
//    Execute Task on Async Thread
//*******************************************************************//

fun async2(executor: ExecutorService, action: () -> Unit): Future<Unit>
        = executor.submit<Unit>(action)

fun async2(action: () -> Unit): Future<Unit>
        = CoreExecutor.submit<Unit>(action)

inline fun <T : Any> async2(
        crossinline action: () -> T?,
        crossinline callback: (result: T?) -> Unit): Future<Unit> {
    return async2(CoreExecutor.executor, action, callback)
}

inline fun <T : Any> async2(
        executor: ExecutorService,
        crossinline action: () -> T?,
        crossinline callback: (result: T?) -> Unit): Future<Unit> {
    return CoreExecutor.submit<Unit> {
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
//    Execute Task on Async Thread With Context Check
//*******************************************************************//


inline fun <T : Any> WeakContext<T>.mainThreadSafe(
        crossinline action: (T) -> Unit) {
    val context = weakRef.get() ?: return
    // may using this.contextAlive
    when (context) {
        is Activity -> if (context.isFinishing) return
        is Fragment -> if (!context.isAdded) return
        is SupportFragment -> if (!context.isAdded) return
    }
    mainThread { action(context) }
}


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

inline fun <T : Any, R : Any> T.asyncSafe2(
        crossinline action: WeakContext<T>.() -> R?,
        crossinline success: (result: R?) -> Unit,
        crossinline failure: (error: Throwable?) -> Unit): Future<Unit> {
    return asyncSafe2(CoreExecutor.executor, action, success, failure)
}

inline fun <T : Any, R : Any> T.asyncSafe2(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R?,
        crossinline success: (result: R?) -> Unit,
        crossinline failure: (error: Throwable?) -> Unit): Future<Unit> {
    val context = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        try {
            val ret = context.action()
            context.mainThreadSafe { success(ret) }
        } catch(e: Exception) {
            context.mainThreadSafe { failure(e) }
        }
    }
}



