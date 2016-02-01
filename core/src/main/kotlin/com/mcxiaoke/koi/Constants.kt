package com.mcxiaoke.koi

import java.nio.charset.Charset
import java.util.regex.Pattern

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 16:50
 */


object Encoding {

    const val ISO_8859_1 = "ISO-8859-1"
    const val US_ASCII = "US-ASCII"
    const val UTF_16 = "UTF-16"
    const val UTF_16BE = "UTF-16BE"
    const val UTF_16LE = "UTF-16LE"
    const val UTF_8 = "UTF-8"
    val CHARSET_ISO_8859_1 = Charset.forName(ISO_8859_1)
    val CHARSET_US_ASCII = Charset.forName(US_ASCII)
    val CHARSET_UTF_16 = Charset.forName(UTF_16)
    val CHARSET_UTF_16BE = Charset.forName(UTF_16BE)
    val CHARSET_UTF_16LE = Charset.forName(UTF_16LE)
    val CHARSET_UTF_8 = Charset.forName(UTF_8)
}

object Const {
    const val FILENAME_NOMEDIA = ".nomedia"
    const val HEAP_SIZE_LARGE = 48 * 1024 * 1024
    const val LINE_SEPARATOR = "\n"
    const val FILE_EXTENSION_SEPARATOR = "."
    const val RESERVED_CHARS = "|\\?*<\":>+[]/'"
    @JvmField val HEX_DIGITS = "0123456789ABCDEF".toCharArray()
    @JvmField val SAFE_FILENAME_PATTERN = Pattern.compile("[\\w%+,./=_-]+")
}