package com.mcxiaoke.koi.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.mcxiaoke.koi.log.logv

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 18:48
 */

abstract class QuickArrayAdapter<T>(ctx: Context, protected val layoutId: Int)
: ArrayAdapterCompat<T>(ctx, arrayListOf<T>()) {

    companion object {
        const val TAG = "QuickArrayAdapter"
    }

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup): View? {
        val binder = getAdapterHelper(convertView, parent, position)
        bind(binder, getItem(position))
        logv(TAG, "getView $binder $position")
        return binder.view
    }

    protected fun getAdapterHelper(convertView: View?,
                                   parent: ViewGroup,
                                   position: Int): QuickAdapterBinder {
        return when (convertView) {
            null -> {
                QuickAdapterBinder(context, layoutId, parent, position)
            }
            else -> {
                val helper = convertView.tag as QuickAdapterBinder
                helper.position = position
                return helper
            }
        }
    }

    abstract fun bind(binder: QuickAdapterBinder, data: T)

}