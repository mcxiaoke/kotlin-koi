package com.mcxiaoke.koi.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.util.Linkify
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mcxiaoke.koi.ext.find
import com.mcxiaoke.koi.ext.inflateLayout

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 18:57
 */

class QuickAdapterBinder constructor(
        val context: Context,
        val layoutId: Int,
        val parent: ViewGroup,
        var position: Int) {

    val view: View

    init {
        view = context.inflateLayout(layoutId, parent, false)
        view.tag = this
    }

    private inline fun <reified T : View> findView(viewId: Int): T {
        return view.find(viewId)
    }

    fun setText(viewId: Int, value: String): QuickAdapterBinder {
        findView<TextView>(viewId).text = value
        return this
    }

    fun setImageResource(viewId: Int, imageResId: Int): QuickAdapterBinder {
        findView<ImageView>(viewId).setImageResource(imageResId)
        return this
    }

    fun setBackgroundColor(viewId: Int, color: Int): QuickAdapterBinder {
        findView<View>(viewId).setBackgroundColor(color)
        return this
    }

    fun setBackgroundRes(viewId: Int, backgroundRes: Int): QuickAdapterBinder {
        findView<View>(viewId).setBackgroundResource(backgroundRes)
        return this
    }

    fun setTextColor(viewId: Int, textColor: Int): QuickAdapterBinder {
        findView<TextView>(viewId).setTextColor(textColor)
        return this
    }

    fun setTextColorRes(viewId: Int, textColorRes: Int): QuickAdapterBinder {
        findView<TextView>(viewId).setTextColor(context.resources.getColor(textColorRes))
        return this
    }

    fun setImageDrawable(viewId: Int, drawable: Drawable): QuickAdapterBinder {
        findView<ImageView>(viewId).setImageDrawable(drawable)
        return this
    }

    fun setImageBitmap(viewId: Int, bitmap: Bitmap): QuickAdapterBinder {
        findView<ImageView>(viewId).setImageBitmap(bitmap)
        return this
    }

    fun setAlpha(viewId: Int, value: Float): QuickAdapterBinder {
        findView<View>(viewId).alpha = value
        return this
    }

    fun setVisible(viewId: Int, visible: Boolean): QuickAdapterBinder {
        findView<View>(viewId).visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun linkify(viewId: Int): QuickAdapterBinder {
        Linkify.addLinks(findView<TextView>(viewId), Linkify.ALL)
        return this
    }

    /** Apply the typeface to the given viewId, and enable subpixel rendering.  */
    fun setTypeface(viewId: Int, typeface: Typeface): QuickAdapterBinder {
        val view = findView<TextView>(viewId)
        view.typeface = typeface
        view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        return this
    }

    fun setTypeface(typeface: Typeface, vararg viewIds: Int): QuickAdapterBinder {
        for (viewId in viewIds) {
            val view = findView<TextView>(viewId)
            view.typeface = typeface
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    fun setProgress(viewId: Int, progress: Int): QuickAdapterBinder {
        findView<ProgressBar>(viewId).progress = progress
        return this
    }

    fun setProgress(viewId: Int, progress: Int, max: Int): QuickAdapterBinder {
        val view = findView<ProgressBar>(viewId)
        view.max = max
        view.progress = progress
        return this
    }

    fun setMax(viewId: Int, max: Int): QuickAdapterBinder {
        findView<ProgressBar>(viewId).max = max
        return this
    }

    fun setRating(viewId: Int, rating: Float): QuickAdapterBinder {
        findView<RatingBar>(viewId).rating = rating
        return this
    }

    fun setRating(viewId: Int, rating: Float, max: Int): QuickAdapterBinder {
        val view = findView<RatingBar>(viewId)
        view.max = max
        view.rating = rating
        return this
    }

    fun setOnClickListener(viewId: Int,
                           listener: View.OnClickListener): QuickAdapterBinder {
        findView<View>(viewId).setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(viewId: Int,
                           listener: View.OnTouchListener): QuickAdapterBinder {
        findView<View>(viewId).setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(viewId: Int,
                               listener: View.OnLongClickListener)
            : QuickAdapterBinder {
        findView<View>(viewId).setOnLongClickListener(listener)
        return this
    }

    fun setOnItemClickListener(viewId: Int,
                               listener: AdapterView.OnItemClickListener)
            : QuickAdapterBinder {
        findView<AdapterView<Adapter>>(viewId).onItemClickListener = listener
        return this
    }

    fun setOnItemLongClickListener(viewId: Int,
                                   listener: AdapterView.OnItemLongClickListener)
            : QuickAdapterBinder {
        findView<AdapterView<Adapter>>(viewId).onItemLongClickListener = listener
        return this
    }

    fun setOnItemSelectedClickListener(viewId: Int,
                                       listener: AdapterView.OnItemSelectedListener)
            : QuickAdapterBinder {
        findView<AdapterView<Adapter>>(viewId).onItemSelectedListener = listener
        return this
    }

    fun setTag(viewId: Int, tag: Any): QuickAdapterBinder {
        findView<View>(viewId).tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any): QuickAdapterBinder {
        findView<View>(viewId).setTag(key, tag)
        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): QuickAdapterBinder {
        (findView<View>(viewId) as Checkable).isChecked = checked
        return this
    }

}