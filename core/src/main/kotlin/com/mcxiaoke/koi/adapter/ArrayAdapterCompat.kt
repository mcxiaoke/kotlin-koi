package com.mcxiaoke.koi.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.*

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
abstract class ArrayAdapterCompat<T>(
        protected val context: Context,
        protected val objects: MutableList<T>)
: BaseAdapter() {

    private val lock = Object()

    constructor(context: Context)
    : this(context, arrayListOf<T>()) {
    }

    constructor(context: Context, objects: Array<T>)
    : this(context, objects.toArrayList()) {
    }

    fun add(newItem: T) {
        synchronized (lock) {
            objects.add(newItem)
        }
        notifyDataSetChanged()
    }

    fun add(index: Int, newItem: T) {
        synchronized (lock) {
            objects.add(index, newItem)
        }
        notifyDataSetChanged()
    }

    operator fun set(index: Int, newItem: T) {
        synchronized (lock) {
            objects.set(index, newItem)
        }
        notifyDataSetChanged()
    }

    fun replaceAll(collection: Collection<T>) {
        clear()
        addAll(collection)
    }

    fun setAll(vararg items: T) {
        clear()
        addAll(*items)
    }

    fun addAll(collection: Collection<T>) {
        synchronized (lock) {
            objects.addAll(collection)
        }
        notifyDataSetChanged()
    }

    fun addAll(vararg items: T) {
        synchronized (lock) {
            Collections.addAll(objects, *items)
        }
        notifyDataSetChanged()
    }

    fun addAll(index: Int, collection: Collection<T>) {
        synchronized (lock) {
            objects.addAll(index, collection)
        }
        notifyDataSetChanged()
    }

    fun addAll(index: Int, vararg items: T) {
        val collection = Arrays.asList(*items)
        synchronized (lock) {
            objects.addAll(index, collection)
        }
        notifyDataSetChanged()
    }

    fun insert(newItem: T, index: Int) {
        synchronized (lock) {
            objects.add(index, newItem)
        }
        notifyDataSetChanged()
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
        notifyDataSetChanged()
    }

    fun removeAt(index: Int) {
        synchronized (lock) {
            objects.removeAt(index)
        }
        notifyDataSetChanged()
    }

    fun removeAll(collection: Collection<*>): Boolean {
        var result = false
        synchronized (lock) {
            result = objects.removeAll(collection)
        }
        notifyDataSetChanged()
        return result
    }

    fun clear() {
        synchronized (lock) {
            objects.clear()
        }
        notifyDataSetChanged()
    }

    fun sort(comparator: Comparator<in T>) {
        synchronized (lock) {
            objects.sortWith(comparator)
        }
        notifyDataSetChanged()
    }

    fun allItems(): List<T> {
        return objects
    }

    fun getPosition(item: T): Int {
        return objects.indexOf(item)
    }

    fun firstItem(): T {
        return objects.first()
    }

    fun lastItem(): T {
        return objects.last()
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

    abstract override fun getView(position: Int,
                                  convertView: View?,
                                  parent: ViewGroup): View?
}
