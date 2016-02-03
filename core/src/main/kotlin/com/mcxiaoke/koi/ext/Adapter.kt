package com.mcxiaoke.koi.ext

import android.app.Activity
import com.mcxiaoke.koi.adapter.QuickAdapter
import com.mcxiaoke.koi.adapter.QuickViewBinder

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 09:34
 */

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        items: Collection<T>,
        bind: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, bind)
    adapter.addAll(items)
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        items: Array<T>,
        bind: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, bind)
    adapter.addAll(items.toList())
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        items: Set<T>,
        bind: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, bind)
    adapter.addAll(items)
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        bind: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    return QuickAdapter(this, layoutId, bind)
}
