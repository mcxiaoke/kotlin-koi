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

inline fun <T : Any> Handler.repeat(delayMillis: Long, startDelayMillis: Long = 0, repeatCount: Int = 0, crossinline action: (Int) -> T) {
    if (startDelayMillis < 0) throw IllegalArgumentException("Start delay must be a positive Int or 0")
    if (repeatCount < 0) throw IllegalArgumentException("Repeat count must be a positive Int or 0")
    var counter = 0
    postDelayed(object : Runnable {
        override fun run() {
            action(counter ++)
            if (repeatCount == 0 || counter < repeatCount) postDelayed(this, delayMillis)
        }
    }, startDelayMillis)
}

fun Handler.cancelAll() = removeCallbacksAndMessages(null)

fun handler(handleMessage: (Message) -> Boolean): Handler {
    return Handler { p -> if (p == null) false else handleMessage(p) }
}
