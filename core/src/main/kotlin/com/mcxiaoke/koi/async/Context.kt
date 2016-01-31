package com.mcxiaoke.koi.async

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Looper
import java.lang.ref.WeakReference
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 14:19
 */


interface Detachable {
    fun isDetached(): Boolean
}

class WeakContext<T>(val weakRef: WeakReference<T>) {

    fun sleep(time: Long) = Thread.sleep(time)

    fun getContext(): T = weakRef.get()

    fun isContextAlive(): Boolean {
        val context = weakRef.get() ?: return false
        return isContextAlive(context)
    }
}

//*******************************************************************//
//    Execute Task on Main Thread
//*******************************************************************//

fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

fun <T> isContextAlive(context: T?): Boolean {
    return when (context) {
        null -> false
        is Activity -> !context.isFinishing
        is Fragment -> context.isAdded
        is SupportFragment -> context.isAdded
        is Detachable -> !context.isDetached()
        else -> true
    }
}

inline fun <T> T.safeFunction(
        crossinline action: () -> Unit):
        () -> Unit {
    return { safeExecute(action) }
}

inline fun <T> T.safeExecute(
        crossinline action: () -> Unit) {
    if (isContextAlive(this)) {
        action()
    }
}

fun delayOnMainThread(delayMillis: Long, action: () -> Unit): Boolean
        = CoreExecutor.mainHandler.postDelayed(action, delayMillis)

fun mainThread(action: () -> Unit): Boolean = CoreExecutor.mainHandler.post(action)

fun Context.mainThread(action: Context.() -> Unit) {
    when {
        isMainThread() -> action()
        else -> CoreExecutor.mainHandler.post { action() }
    }
}

inline fun Fragment.mainThread(crossinline action: () -> Unit) {
    activity.mainThread { action() }
}

inline fun SupportFragment.mainThread(crossinline action: () -> Unit) {
    activity.mainThread { action() }
}

inline fun <T> WeakContext<T>.mainThread(crossinline action: (T) -> Unit) {
    val context = weakRef.get() ?: return
    when {
        isMainThread() -> action(context)
        else -> CoreExecutor.mainHandler.post { action(context) }
    }
}

inline fun <T> WeakContext<T>.safe(
        crossinline action: WeakContext<T>.(T) -> Unit) {
    val context = weakRef.get() ?: return
    if (isContextAlive()) {
        action(context)
    }
}

inline fun <T> WeakContext<T>.mainThreadSafe(
        crossinline action: (T) -> Unit) {
    val context = weakRef.get() ?: return
    if (isContextAlive()) {
        mainThread { action(context) }
    }
}

//fun Context.mainThreadSafe(action: Context.() -> Unit) {
//    WeakContext(WeakReference(this)).mainThreadSafe(action)
//}
//
//inline fun Fragment.mainThreadSafe(crossinline action: () -> Unit) {
//    activity.mainThreadSafe { action() }
//}
//
//inline fun SupportFragment.mainThreadSafe(crossinline action: () -> Unit) {
//    activity.mainThreadSafe { action() }
//}

