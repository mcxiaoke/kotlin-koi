package com.mcxiaoke.koi.ext

import android.os.Handler
import android.os.Message

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:58
 */
inline fun <T : Any> Handler.atNow(crossinline action: () -> T): Boolean
        = post({ action() })

inline fun <T : Any> Handler.atFront(crossinline action: () -> T): Boolean
        = postAtFrontOfQueue({ action() })

inline fun <T : Any> Handler.atTime(uptimeMillis: Long, crossinline action: () -> T): Boolean
        = postAtTime({ action() }, uptimeMillis)

inline fun <T : Any> Handler.delayed(delayMillis: Long, crossinline action: () -> T): Boolean
        = postDelayed({ action() }, delayMillis)

fun handler(handleMessage: (Message) -> Boolean): Handler {
    return Handler { p -> if (p == null) false else handleMessage(p) }
}
