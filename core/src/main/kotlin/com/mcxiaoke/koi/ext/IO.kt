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

fun Closeable?.closeQuietly() {
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
fun InputStream.readString(encoding: String = Encoding.UTF_8): String {
    var buffer = charArrayOf()
    this.reader(encoding).read(buffer)
    return String(buffer)
}

@Throws(IOException::class)
@JvmOverloads fun InputStream.readString(charset: Charset = Charsets.UTF_8): String {
    var buffer = charArrayOf()
    this.reader(charset).read(buffer)
    return String(buffer)
}

@Throws(IOException::class)
@JvmOverloads fun File.readString(charset: Charset = Charsets.UTF_8): String {
    var input = FileInputStream(this)
    return input.readString(charset)
}

@Throws(IOException::class)
fun InputStream.readLines(input: Reader): List<String> {
    return BufferedReader(input).lineSequence().toList()
}

@Throws(IOException::class)
@JvmOverloads fun InputStream.readLines(charset: Charset = Charsets.UTF_8): List<String> {
    return readLines(reader(charset))
}

@Throws(IOException::class)
@JvmOverloads fun File.readLines(charset: Charset = Charsets.UTF_8): List<String> {
    return FileInputStream(this).readLines(charset)
}

@Throws(IOException::class)
@JvmOverloads fun OutputStream.writeLines(lines: Collection<*>?,
                                          charset: Charset = Charsets.UTF_8,
                                          lineSeparator: String? = Const.LINE_SEPARATOR) {
    lines?.forEach { line ->
        if (line != null) {
            this.write(line.toString().toByteArray(charset))
        }
        this.write(lineSeparator?.toByteArray(charset))
    }
}

@Throws(IOException::class)
@JvmOverloads fun File.writeLines(lines: Collection<*>?,
                                  charset: Charset = Charsets.UTF_8,
                                  lineSeparator: String? = Const.LINE_SEPARATOR) {
    return FileOutputStream(this).writeLines(lines, charset, lineSeparator)
}

@Throws(IOException::class)
fun File?.copyTo(destFile: File) {
    if (this == null) {
        return
    }
    if (!destFile.exists()) {
        destFile.createNewFile()
    }

    var source: FileChannel? = null
    var destination: FileChannel? = null
    try {
        source = FileInputStream(this).channel
        destination = FileOutputStream(destFile).channel
        destination?.transferFrom(source, 0, source.size())
    } finally {
        source?.closeQuietly()
        destination?.closeQuietly()
    }
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



