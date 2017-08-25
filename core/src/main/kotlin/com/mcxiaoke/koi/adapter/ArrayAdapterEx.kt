package com.mcxiaoke.koi.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.Comparator

/**
 * 从系统源码复制出来，精简了一些无用的方法，添加了一些实用的方法
 * public List getAllItems() //获取Adapter里所有的数据项
 * public void add(int index, T object) // 指定位置插入数据
 * public void set(int index, T object) // 指定位置插入数据
 * public void addAll(int index, Collection collection)  //指定位置批量插入数据
 * public void addAll(int index, T... items) //指定位置批量插入数据
 * public boolean contains(T object) //是否包含某个数据项
 * public int indexOf(T object) // 同上，返回index，不包含就返回-1
 * public void removeAt(int index) // 移除某个位置的数据项

 * @param
 */

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 18:21
 */
abstract class ArrayAdapterEx<T>(
        protected val context: Context,
        protected val objects: MutableList<T>)
: BaseAdapter() {

    private val lock = Object()
    protected val inflater: LayoutInflater = LayoutInflater.from(context)
    protected val resources: Resources = context.resources

    var notifyChange = true

    constructor(context: Context)
    : this(context, arrayListOf<T>())

    constructor(context: Context, objects: Array<T>)
    : this(context, objects.toMutableList())

    constructor(context: Context, objects: Collection<T>)
    : this(context, objects.toMutableList())

    fun addAll(items: Collection<T>): ArrayAdapterEx<T> {
        synchronized (lock) {
            objects.addAll(items)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return this
    }

    fun addAll(index: Int, collection: Collection<T>): ArrayAdapterEx<T> {
        synchronized (lock) {
            objects.addAll(index, collection)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return this
    }

    fun removeAll(items: Collection<T>): Boolean {
        var result = false
        synchronized (lock) {
            result = objects.removeAll(items)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return result
    }

    fun clear(): ArrayAdapterEx<T> {
        synchronized (lock) {
            objects.clear()
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return this
    }

    fun sort(comparator: Comparator<in T>): ArrayAdapterEx<T> {
        synchronized (lock) {
            objects.sortWith(comparator)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return this
    }

    fun allItems(): List<T> {
        return objects
    }


    fun add(newItem: T): ArrayAdapterEx<T> {
        synchronized (lock) {
            objects.add(newItem)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return this
    }

    fun add(index: Int, newItem: T): ArrayAdapterEx<T> {
        synchronized (lock) {
            objects.add(index, newItem)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return this
    }

    operator fun set(index: Int, newItem: T): ArrayAdapterEx<T> {
        synchronized (lock) {
            objects.set(index, newItem)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
        return this
    }

    operator fun contains(newItem: T): Boolean {
        synchronized (lock) {
            return objects.contains(newItem)
        }
    }

    fun indexOf(newItem: T): Int {
        synchronized (lock) {
            return objects.indexOf(newItem)
        }
    }

    fun remove(newItem: T) {
        synchronized (lock) {
            objects.remove(newItem)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
    }

    fun removeAt(index: Int) {
        synchronized (lock) {
            objects.removeAt(index)
        }
        if (notifyChange) {
            notifyDataSetChanged()
        }
    }

    fun getPosition(item: T): Int {
        return objects.indexOf(item)
    }

    fun lastOrNull(): T? {
        return objects.lastOrNull()
    }

    fun firstOrNull(): T? {
        return objects.firstOrNull()
    }

    fun getItemOrNull(position: Int): T? {
        return objects.getOrNull(position)
    }

    override fun getCount(): Int {
        return objects.size
    }

    override fun getItem(position: Int): T {
        return objects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup): View? {
        val view: View
        when (convertView) {
            null -> view = newView(position, parent)
            else -> view = convertView
        }
        bindView(position, view)
        return view
    }

    abstract fun newView(position: Int, parent: ViewGroup): View
    abstract fun bindView(position: Int, view: View)
}
