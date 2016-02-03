package com.mcxiaoke.koi.samples

import com.mcxiaoke.koi.Encoding
import com.mcxiaoke.koi.ext.*
import java.io.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 21:52
 */

class IOExtensionSample {

    // available for Closeable
    fun closeableSample() {
        val input = FileInputStream(File("readme.txt"))
        try {
            val string = input.readString("UTF-8")
        } catch(e: IOException) {
            e.printStackTrace()
        } finally {
            input.closeQuietly()
        }
    }

    // simple way, equal to closeableSample
    // InputStream.doSafe{}
    fun doSafeSample() {
        val input = FileInputStream(File("readme.txt"))
        input.doSafe {
            val string = readString("UTF-8")
        }
    }


    // available for InputStream/Reader
    fun readStringAndList1() {
        val input = FileInputStream(File("readme.txt"))
        try {
            val reader = input.reader(Encoding.CHARSET_UTF_8)

            val string1 = input.readString(Encoding.UTF_8)
            val string2 = input.readString(Encoding.CHARSET_UTF_8)

            val list1 = input.readList()
            val list2 = input.readList(Encoding.CHARSET_UTF_8)

        } catch(e: IOException) {

        } finally {
            input.closeQuietly()
        }
    }

    // available for InputStream/Reader
    //equal to readStringAndList1
    fun readStringAndList2() {
        val input = FileInputStream(File("readme.txt"))
        input.doSafe {
            val reader = reader(Encoding.CHARSET_UTF_8)

            val string1 = readString(Encoding.UTF_8)
            val string2 = readString(Encoding.CHARSET_UTF_8)

            val list1 = readList()
            val list2 = readList(Encoding.CHARSET_UTF_8)

        }

    }

    fun writeStringAndList() {
        val output = FileOutputStream("output.txt")
        output.doSafe {
            output.writeString("hello, world")
            output.writeString("你好，世界", charset = Encoding.CHARSET_UTF_8)

            val list1 = listOf<Int>(1, 2, 3, 4, 5)
            val list2 = (1..8).map { "Item No.$it" }
            output.writeList(list1, charset = Encoding.CHARSET_UTF_8)
            output.writeList(list2)


        }
    }

    fun fileReadWrite() {
        val directory = File("/Users/koi/workspace")
        val file = File("some.txt")

        val text1 = file.readText()
        val text2 = file.readString(Encoding.CHARSET_UTF_8)
        val list1 = file.readList()
        val list2 = file.readLines(Encoding.CHARSET_UTF_8)

        file.writeText("hello, world")
        file.writeList(list1)
        file.writeList(list2, Encoding.CHARSET_UTF_8)

        val v1 = file.relativeToOrNull(directory)
        val v2 = file.toRelativeString(directory)

        // clean files in directory
        directory.clean()


        val file1 = File("a.txt")
        val file2 = File("b.txt")
        file1.copyTo(file2, overwrite = false)
    }

}