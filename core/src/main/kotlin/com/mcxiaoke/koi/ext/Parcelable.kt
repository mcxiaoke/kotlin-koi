package com.mcxiaoke.koi.ext

import android.os.Parcel
import android.os.Parcelable

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 11:15
 */

inline fun <reified T : Parcelable> createParcel(crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }