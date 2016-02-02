package com.mcxiaoke.koi.samples

import com.mcxiaoke.koi.ext.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 21:31
 */
class DateExtensionSample {


    fun dateSample() {
        val nowString = dateNow()
        val date = dateParse("2016-02-02 20:30:45")
        val dateStr1 = date.asString()
        val dateStr2 = date.asString(SimpleDateFormat("yyyyMMdd.HHmmss"))
        val dateStr3 = date.asString("yyyy-MM-dd-HH-mm-ss")

        val timestamp1 = timestamp()
        // equal to
        val timestamp2 = System.currentTimeMillis()
        val dateStr4 = timestamp1.asDateString()
    }

}
