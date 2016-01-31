package com.mcxiaoke.koi.async

import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import android.support.v4.app.Fragment as SupportFragment

/**
 * Author: mcxiaoke
 * Date:  2016/1/29 8:23
 */


//*******************************************************************//
//    Extension Async Functions With Context Check
//*******************************************************************//

inline fun <T, R> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R): Future<Unit> {
    return asyncSafe(CoreExecutor.executor, action)
}

inline fun <T, R> T.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R): Future<Unit> {
    val weakCtx = WeakContext(WeakReference(this))
    return executor.submit<Unit> { weakCtx.safe { action() } }
}

fun <T, R> T.asyncSafe(
        action: WeakContext<T>.() -> R,
        callback: (result: R?, error: Throwable?) -> Unit): Future<Unit> {
    return asyncSafe(CoreExecutor.executor, action, callback)
}

fun <T, R> T.asyncSafe(
        executor: ExecutorService,
        action: WeakContext<T>.() -> R,
        callback: (result: R?, error: Throwable?) -> Unit): Future<Unit> {
    val weakCtx = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        //        try {
        //            val ret = weakCtx.action()
        //            weakCtx.mainThreadSafe { callback(ret, null) }
        //        } catch(ex: Exception) {
        //            weakCtx.mainThreadSafe { callback(null, ex) }
        //        }

        weakCtx.safe {
            try {
                val ret = action()
                mainThreadSafe { callback(ret, null) }
            } catch(ex: Exception) {
                mainThreadSafe { callback(null, ex) }
            }
        }
    }


}

inline fun <T, R> T.asyncSafe(
        crossinline action: WeakContext<T>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    return asyncSafe(CoreExecutor.executor, action, success, failure)
}

inline fun <T, R> T.asyncSafe(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R,
        crossinline success: (result: R) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    val weakCtx = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        weakCtx.safe {
            try {
                val ret = action()
                mainThreadSafe { success(ret) }
            } catch(ex: Exception) {
                mainThreadSafe { failure(ex) }
            }
        }
    }
}



//*******************************************************************//
//    Async Functions Nullable With Context Check
//*******************************************************************//

inline fun <T, R> T.asyncSafe2(
        crossinline action: WeakContext<T>.() -> R?,
        noinline success: ((result: R?) -> Unit)?,
        noinline failure: ((error: Throwable?) -> Unit)?): Future<Unit> {
    return asyncSafe2(CoreExecutor.executor, action, success, failure)
}

inline fun <T, R> T.asyncSafe2(
        executor: ExecutorService,
        crossinline action: WeakContext<T>.() -> R?,
        noinline success: ((result: R?) -> Unit)?,
        noinline failure: ((error: Throwable?) -> Unit)?): Future<Unit> {
    val weakCtx = WeakContext(WeakReference(this))
    return executor.submit<Unit> {
        weakCtx.safe {
            try {
                val ret = action()
                mainThreadSafe { success?.invoke(ret) }
            } catch(e: Exception) {
                mainThreadSafe { failure?.invoke(e) }
            }
        }

    }
}

//*******************************************************************//
//    Extension Async Functions With Delay
//*******************************************************************//

fun <T, R> T.asyncDelay(
        delayInMillis: Long,
        action: WeakContext<T>.() -> R): Unit {
    asyncDelay(CoreExecutor.executor, delayInMillis, action)
}

fun <T, R> T.asyncDelay(
        executor: ExecutorService,
        delayInMillis: Long,
        action: WeakContext<T>.() -> R): Unit {
    val weakCtx = WeakContext(WeakReference(this))
    CoreExecutor.mainHandler.postDelayed({
        executor.submit<Unit> {
            weakCtx.safe { action() }
        }
    }, delayInMillis)
}

inline fun <T, R> T.asyncDelay(
        delayInMillis: Long,
        crossinline action: WeakContext<T>.() -> R?,
        crossinline callback: (result: R?) -> Unit): Unit {
    return asyncDelay(CoreExecutor.executor, delayInMillis, action, callback)
}

inline fun <T, R> T.asyncDelay(
        executor: ExecutorService,
        delayInMillis: Long,
        crossinline action: WeakContext<T>.() -> R?,
        crossinline callback: (result: R?) -> Unit): Unit {
    val weakCtx = WeakContext(WeakReference(this))
    CoreExecutor.mainHandler.postDelayed({
        executor.submit<Unit> {
            weakCtx.safe {
                val ret = action()
                mainThreadSafe { callback(ret) }
            }
        }
    }, delayInMillis)
}

inline fun <T, R> T.asyncDelay(
        delayInMillis: Long,
        crossinline action: WeakContext<T>.() -> R?,
        crossinline success: (result: R?) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Unit {
    return asyncDelay(CoreExecutor.executor, delayInMillis, action, success, failure)
}

inline fun <T, R> T.asyncDelay(
        executor: ExecutorService,
        delayInMillis: Long,
        crossinline action: WeakContext<T>.() -> R?,
        crossinline success: (result: R?) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Unit {

    val weakCtx = WeakContext(WeakReference(this))
    CoreExecutor.mainHandler.postDelayed({
        executor.submit<Unit> {
            weakCtx.safe {
                try {
                    val ret = action()
                    mainThreadSafe { success(ret) }
                } catch(e: Exception) {
                    mainThreadSafe { failure(e) }
                }
            }
        }
    }, delayInMillis)
}

//*******************************************************************//
//   Extension Async Functions Without Context Check
//*******************************************************************//

fun <T, R> T.asyncUnsafe(
        action: () -> R?): Future<R> {
    return asyncUnsafe(CoreExecutor.executor, action)
}

fun <T, R> T.asyncUnsafe(
        executor: ExecutorService,
        action: () -> R?): Future<R> {
    return executor.submit<R>(action)
}

inline fun <T, R> T.asyncUnsafe(
        crossinline action: () -> R?,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit> {
    return asyncUnsafe(CoreExecutor.executor, action, callback)
}

inline fun <T, R> T.asyncUnsafe(
        executor: ExecutorService,
        crossinline action: () -> R?,
        crossinline callback: (result: R?, error: Throwable?) -> Unit): Future<Unit> {
    return executor.submit<Unit> {
        try {
            val ret = action()
            mainThread { callback(ret, null) }
        } catch(ex: Exception) {
            mainThread { callback(null, ex) }
        }
    }
}

inline fun <T, R> T.asyncUnsafe(
        crossinline action: () -> R?,
        crossinline success: (result: R?) -> Unit,
        crossinline failure: (error: Throwable) -> Unit): Future<Unit> {
    return asyncUnsafe(CoreExecutor.executor, action, success, failure)
}

inline fun <T, R> T.asyncUnsafe(
        executor: ExecutorService,
        crossinline action: () -> R?,
        crossinline success: (result: R?) -> Unit,
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



