package com.mcxiaoke.koi.async

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

//*******************************************************************//
//    Global Async Functions Simple
//*******************************************************************//

fun <T : Any> async2(executor: ExecutorService, action: () -> T): Future<T>
        = executor.submit<T>(action)

fun <T : Any> async2(action: () -> T): Future<T>
        = CoreExecutor.submit(action)

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
//    Context Extension Async Functions With Context Check
//*******************************************************************//

inline fun <T : Context, R : Any> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R): Future<R> {
    return asyncSafe(CoreExecutor.executor, action)
}

inline fun <T : Context, R : Any> T.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R): Future<R> {
    val context = WeakContext(WeakReference(this))
    return executor.submit<R> { context.action() }
}

inline fun <T : Context, R : Any> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit> {
    return asyncSafe(CoreExecutor.executor, action, callback)
}

inline fun <T : Context, R : Any> T.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit> {
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

inline fun <T : Context, R : Any> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    return asyncSafe(CoreExecutor.executor, action, success, failure)
}

inline fun <T : Context, R : Any> T.asyncSafe(
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
//    Fragment Extension Async Functions With Context Check
//*******************************************************************//

inline fun <R : Any> Fragment.asyncSafe(
        crossinline action: WeakContext<Context>.() -> R): Future<R>
        = activity.asyncSafe(action)

inline fun <R : Any> Fragment.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R): Future<R>
        = activity.asyncSafe(executor, action)

inline fun <R : Any> Fragment.asyncSafe(
        crossinline action: WeakContext<Context>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit>
        = activity.asyncSafe(action, callback)

inline fun <R : Any> Fragment.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit>
        = activity.asyncSafe(executor, action, callback)

inline fun <R : Any> Fragment.asyncSafe(
        crossinline action: WeakContext<Context>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit>
        = activity.asyncSafe(action, success, failure)

inline fun <R : Any> Fragment.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit>
        = activity.asyncSafe(executor, action, success, failure)

//*******************************************************************//
//    V4 Fragment Extension Async Functions With Context Check
//*******************************************************************//

inline fun <R : Any> android.support.v4.app.Fragment.asyncSafe(
        crossinline action: WeakContext<Context>.() -> R): Future<R>
        = activity.asyncSafe(action)

inline fun <R : Any> android.support.v4.app.Fragment.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R): Future<R>
        = activity.asyncSafe(executor, action)

inline fun <R : Any> android.support.v4.app.Fragment.asyncSafe(
        crossinline action: WeakContext<Context>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit>
        = activity.asyncSafe(action, callback)

inline fun <R : Any> android.support.v4.app.Fragment.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit>
        = activity.asyncSafe(executor, action, callback)

inline fun <R : Any> android.support.v4.app.Fragment.asyncSafe(
        crossinline action: WeakContext<Context>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit>
        = activity.asyncSafe(action, success, failure)

inline fun <R : Any> android.support.v4.app.Fragment.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit>
        = activity.asyncSafe(executor, action, success, failure)

//*******************************************************************//
//    Async Nullable Functions With Context Check
//*******************************************************************//

inline fun <R : Any> Context.asyncNullSafe(
        crossinline action: WeakContext<Context>.() -> R?,
        noinline callback: ((result: R?, error: Throwable?) -> Unit)?): Future<Unit>? {
    return asyncNullSafe(CoreExecutor.executor, action, callback)
}

inline fun <R : Any> Context.asyncNullSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R?,
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

inline fun <R : Any> Context.asyncSafe2(
        crossinline action: WeakContext<Context>.() -> R?,
        noinline success: ((result: R?) -> Unit)?,
        noinline failure: ((error: Throwable?) -> Unit)?): Future<Unit> {
    return asyncSafe2(CoreExecutor.executor, action, success, failure)
}

inline fun <R : Any> Context.asyncSafe2(
        executor: ExecutorService,
        crossinline action: WeakContext<Context>.() -> R?,
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



