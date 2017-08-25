package com.mcxiaoke.koi.ext

import com.mcxiaoke.koi.Const
import com.mcxiaoke.koi.Encoding
import java.io.*
import java.net.HttpURLConnection
import java.net.URLConnection
import java.nio.channels.FileChannel
import java.nio.charset.Charset

/**
 * User: mcxiaoke
 * Date: 16/1/22
 * Time: 14:48
 */

fun <T : Closeable> T.doSafe(action: T.() -> Unit) {
    try {
        action()
    } catch(e: IOException) {
    } finally {
        closeQuietly()
    }
}

fun <T : Closeable> T?.closeQuietly() {
    try {
        this?.close()
    } catch (ignored: IOException) {
        // ignore
    }
}

fun URLConnection.close() {
    if (this is HttpURLConnection) {
        this.disconnect()
    }
}

@Throws(IOException::class)
fun Reader.readString(): String {
    val buffer = charArrayOf()
    this.read(buffer)
    return String(buffer)
}

@Throws(IOException::class)
@JvmOverloads fun InputStream.readString(charset: Charset = Charsets.UTF_8): String {
    val buffer = charArrayOf()
    this.reader(charset).read(buffer)
    return String(buffer)
}

@Throws(IOException::class)
@JvmOverloads fun File.readString(charset: Charset = Charsets.UTF_8): String {
    val input = FileInputStream(this)
    return input.readString(charset)
}

@Throws(IOException::class)
fun Reader.readList(): List<String> {
    return BufferedReader(this).lineSequence().toList()
}

@Throws(IOException::class)
@JvmOverloads fun InputStream.readList(charset: Charset = Charsets.UTF_8): List<String> {
    return this.reader(charset).readList()
}

@Throws(IOException::class)
@JvmOverloads fun File.readList(charset: Charset = Charsets.UTF_8): List<String> {
    return FileInputStream(this).readList(charset)
}

@Throws(IOException::class)
@JvmOverloads fun OutputStream.writeString(string: String, charset: Charset = Charsets.UTF_8) {
    this.writer(charset).write(string)
}


@Throws(IOException::class)
@JvmOverloads fun Writer.writeList(lines: Collection<*>?,
                                   lineSeparator: String = Const.LINE_SEPARATOR) {
    lines?.forEach { line ->
        if (line != null) {
            this.write(line.toString())
        }
        this.write(lineSeparator)
    }
}

@Throws(IOException::class)
@JvmOverloads fun OutputStream.writeList(lines: Collection<*>?,
                                         charset: Charset = Charsets.UTF_8,
                                         lineSeparator: String = Const.LINE_SEPARATOR) {
    lines?.forEach { line ->
        if (line != null) {
            this.write(line.toString().toByteArray(charset))
        }
        this.write(lineSeparator.toByteArray(charset))
    }
}

@Throws(IOException::class)
@JvmOverloads fun File.writeList(lines: Collection<*>?,
                                 charset: Charset = Charsets.UTF_8,
                                 lineSeparator: String = Const.LINE_SEPARATOR) {
    return FileOutputStream(this).writeList(lines, charset, lineSeparator)
}

fun File.clean(): Boolean {
    val file = this
    if (!file.exists()) {
        return true
    }
    if (file.isFile) {
        return file.delete()
    }
    if (!file.isDirectory) {
        return false
    }
    for (f in file.listFiles()) {
        if (f.isFile) {
            f.delete()
        } else if (f.isDirectory) {
            f.clean()
        }
    }
    return file.delete()
}



