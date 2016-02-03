package com.mcxiaoke.koi.samples

import android.os.Handler
import com.mcxiaoke.koi.ext.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 21:37
 */

class HandlerExtensionSample {

    // available for Handler
    // short name for functions
    fun handlerFunctions() {
        val handler = Handler()

        handler.atNow { print("perform action now") }
        // equal to
        handler.post { print("perform action now") }

        handler.atFront { print("perform action at first") }
        // equal to
        handler.postAtFrontOfQueue { print("perform action at first") }


        handler.atTime(timestamp() + 5000, { print("perform action after 5s") })
        // equal to
        handler.postAtTime({ print("perform action after 5s") }, 5000)

        handler.delayed(3000, { print("perform action after 5s") })
        // equal to
        handler.postDelayed({ print("perform action after 5s") }, 3000)


    }
}
