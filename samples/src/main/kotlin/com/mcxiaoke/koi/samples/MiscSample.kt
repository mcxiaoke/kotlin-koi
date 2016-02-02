package com.mcxiaoke.koi.samples

import android.os.Parcel
import android.os.Parcelable
import com.mcxiaoke.koi.HASH
import com.mcxiaoke.koi.assert.throwIfEmpty
import com.mcxiaoke.koi.assert.throwIfFalse
import com.mcxiaoke.koi.assert.throwIfNull
import com.mcxiaoke.koi.assert.throwIfTrue
import com.mcxiaoke.koi.ext.*
import com.mcxiaoke.koi.threadName
import com.mcxiaoke.koi.utils.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 22:43
 */
class OtherExtensionSample {

    fun sample1() {
        val number = 179325344324902187L
        println(number.readableByteCount())

        val bytes = byteArrayOf(1, 7, 0, 8, 9, 4, 125)
        println(bytes.hexString())
    }

    fun stringSample() {
        val string = "hello, little cat!"
        val quotedString = string.quote()
        val isBlank = string.isBlank()
        val hexBytes = string.toHexBytes()
        val s1 = string.trimAllWhitespace()
        val c = string.containsWhitespace()

        val url = "https://github.com/mcxiaoke/kotlin-koi?year=2016&encoding=utf8&a=b#changelog"
        val urlNoQuery = url.withoutQuery()

        val isNameSafe = url.isNameSafe()
        val fileName = url.toSafeFileName()
        val queries = url.toQueries()

        val path = "/Users/koi/workspace/String.kt"
        val baseName = path.fileNameWithoutExtension()
        val extension = path.fileExtension()
        val name = path.fileName()
    }

    fun coreFunctions() {
        val tn = threadName()
    }

    fun cryptoSample() {
        val md5 = HASH.md5("hello, world")
        val sha1 = HASH.sha1("hello, world")
        val sha256 = HASH.sha256("hello, world")

    }

    fun apiLevelSample() {
        val v = currentVersion() // Build.VERSION.SDK_INT
        val ics = icsOrNewer()
        val kk = kitkatOrNewer()
        val bkk = beforeKitkat()
        val lol = lollipopOrNewer()
        val mar = marshmallowOrNewer()
    }

    fun deviceSample() {
        val a = isLargeHeap
        val b = noSdcard()
        val c = noFreeSpace(needSize = 10 * 1024 * 1024L)
        val d = freeSpace()
    }

    fun preconditions() {
        throwIfEmpty(listOf(), "collection is null or empty")
        throwIfNull(null, "object is null")
        throwIfTrue(currentVersion() == 10, "result is true")
        throwIfFalse(currentVersion() < 4, "result is false")
    }

}

data class Person(val name: String, val age: Int) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
    }

    override fun describeContents(): Int = 0

    protected constructor(p: Parcel) : this(name = p.readString(), age = p.readInt()) {
    }

    companion object {
        @JvmField val CREATOR = createParcel { Person(it) }
    }
}
