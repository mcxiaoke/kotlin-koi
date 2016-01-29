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


fun Context.mainThread(action: Context.() -> Unit) {
    if (isMainThread()) action() else mainHandler.post { action() }
}

inline fun Fragment.mainThread(crossinline action: () -> Unit) {
    activity?.mainThread { action() }
}

inline fun SupportFragment.mainThread(crossinline action: () -> Unit) {
    activity?.mainThread { action() }
}

class WeakContext<T>(val weakRef: WeakReference<T>)

fun <T> WeakContext<T>.mainThread(action: (T) -> Unit) {
    val ref = weakRef.get() ?: return
    if (isMainThread()) {
        action(ref)
    } else {
        mainHandler.post { action(ref) }
    }
}

fun <T : Any> WeakContext<T>.mainThreadSafe(action: (T) -> Unit) {
    val context = weakRef.get() ?: return
    when (context) {
        is Activity -> if (context.isFinishing) return
        is Fragment -> if (!context.isAdded) return
        is SupportFragment -> if (!context.isAdded) return
    }
    mainThread { action(context) }
}

inline fun <T : Any> WeakContext<T>.asyncSafe(crossinline action: (T) -> T?,
                                              crossinline callback: (result: T?) -> Unit) {
    val caller = weakRef.get() ?: return
    CoreExecutor.submit<Unit> {
        val ret = action(caller)
        mainThreadSafe { callback(ret) }
    }
}

fun <T : Activity> WeakContext<T>.activityMainThread(action: (T) -> Unit) {
    val activity = weakRef.get() ?: return
    if (activity.isFinishing) return
    activity.runOnUiThread { action(activity) }
}

fun <T : Activity> WeakContext<T>.activityMainThreadWithContext(action: Context.(T) -> Unit) {
    val activity = weakRef.get() ?: return
    if (activity.isFinishing) return
    activity.runOnUiThread { activity.action(activity) }
}


fun <T, R> T.async2(action: WeakContext<T>.() -> R): Future<R> {
    val context = WeakContext(WeakReference(this))
    return CoreExecutor.submit { context.action() }
}

fun <T, R> T.async2(executor: ExecutorService, action: WeakContext<T>.() -> R): Future<R> {
    val context = WeakContext(WeakReference(this))
    return executor.submit<R> { context.action() }
}