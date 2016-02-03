package com.mcxiaoke.koi.samples

import android.app.Activity
import android.os.Bundle
import com.mcxiaoke.koi.ext.Bundle

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 20:48
 */

class BundleExtensionSample {


    // available in any where
    fun bundleExtension() {
        // easy way to create bundle
        val bundle = Bundle {
            putString("key", "value")
            putInt("int", 12345)
            putBoolean("boolean", false)
            putIntArray("intArray", intArrayOf(1, 2, 3, 4, 5))
            putStringArrayList("strings", arrayListOf("Hello", "World", "Cat"))
        }

        // equal to using with
        val bundle2 = Bundle()
        with(bundle2) {
            putString("key", "value")
            putInt("int", 12345)
            putBoolean("boolean", false)
            putIntArray("intArray", intArrayOf(1, 2, 3, 4, 5))
            putStringArrayList("strings", arrayListOf("Hello", "World", "Cat"))
        }
    }
}