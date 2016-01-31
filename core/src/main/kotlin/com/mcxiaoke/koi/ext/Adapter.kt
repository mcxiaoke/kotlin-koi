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
        renderer: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, renderer)
    adapter.addAll(items)
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        items: Array<T>,
        renderer: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, renderer)
    adapter.addAll(items)
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        items: Iterable<T>,
        renderer: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, renderer)
    adapter.addAll(items)
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        items: Sequence<T>,
        renderer: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, renderer)
    adapter.addAll(items)
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        items: Set<T>,
        renderer: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    val adapter = quickAdapterOf(layoutId, renderer)
    adapter.addAll(items)
    return adapter
}

fun <T : Any> Activity.quickAdapterOf(
        layoutId: Int,
        renderer: ((QuickViewBinder, T) -> Unit))
        : QuickAdapter<T> {
    return QuickAdapter(this, layoutId, renderer)
}
