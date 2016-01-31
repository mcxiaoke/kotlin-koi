package com.mcxiaoke.koi.async

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Looper
import java.lang.ref.WeakReference

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 14:19
 */


interface Detachable {
    val isDetached: Boolean
}

class WeakContext<T>(val weakRef: WeakReference<T>) {

    val <T : Any> WeakContext<T>.hasValidContext: Boolean
        get() {
            val context = weakRef.get() ?: return false
            return checkContext(context)
        }
}

//*******************************************************************//
//    Execute Task on Main Thread
//*******************************************************************//

fun Context.mainThread(action: Context.() -> Unit) {
    if (isMainThread()) action() else CoreExecutor.mainHandler.post { action() }
}

inline fun Fragment.mainThread(crossinline action: () -> Unit) {
    activity.mainThread { action() }
}

inline fun android.support.v4.app.Fragment.mainThread(crossinline action: () -> Unit) {
    activity.mainThread { action() }
}

inline fun <T> WeakContext<T>.mainThread(crossinline action: (T) -> Unit) {
    val context = weakRef.get() ?: return
    if (isMainThread()) {
        action(context)
    } else {
        CoreExecutor.mainHandler.post { action(context) }
    }
}

inline fun <T : Context> T.safeFunc(
        crossinline action: () -> Unit):
        () -> Unit {
    return { safe(action) }
}

inline fun <T : Context> T.safe(
        crossinline action: () -> Unit) {
    if (checkContext(this)) {
        action()
    }
}

inline fun <T : Any> WeakContext<T>.mainThreadSafe(
        crossinline action: (T) -> Unit) {
    val context = weakRef.get() ?: return
    if (checkContext(context)) {
        mainThread { action(context) }
    }
}

fun Context.mainThreadSafe(action: Context.() -> Unit) {
    WeakContext(WeakReference(this)).mainThreadSafe(action)
}

inline fun Fragment.mainThreadSafe(crossinline action: () -> Unit) {
    activity.mainThreadSafe { action() }
}

inline fun android.support.v4.app.Fragment.mainThreadSafe(crossinline action: () -> Unit) {
    activity.mainThreadSafe { action() }
}

fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

fun mainThread(action: () -> Unit): Boolean = CoreExecutor.mainHandler.post(action)

fun delayOnMainThread(delayMillis: Long, action: () -> Unit): Boolean
        = CoreExecutor.mainHandler.postDelayed(action, delayMillis)

fun <T : Any> checkContext(context: T): Boolean {
    return when (context) {
        is Activity -> !context.isFinishing
        is Fragment -> context.isAdded
        is android.support.v4.app.Fragment -> context.isAdded
        is Detachable -> !context.isDetached
        else -> true
    }
}
