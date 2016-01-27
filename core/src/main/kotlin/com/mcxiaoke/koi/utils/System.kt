package com.mcxiaoke.koi.utils

import android.content.Intent
import android.os.BatteryManager
import android.os.Environment
import android.os.StatFs
import com.mcxiaoke.koi.Const

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 11:06
 */
val isLargeHeap: Boolean
    get() = Runtime.getRuntime().maxMemory() > Const.HEAP_SIZE_LARGE


fun noSdcard(): Boolean {
    return Environment.MEDIA_MOUNTED != Environment.getExternalStorageState()
}

/**
 * check if free size of SDCARD and CACHE dir is OK

 * @param needSize how much space should release at least
 * *
 * @return true if has enough space
 */
fun noFreeSpace(needSize: Long): Boolean {
    val freeSpace = freeSpace()
    return freeSpace < needSize * 3
}

fun freeSpace(): Long {
    val stat = StatFs(Environment.getExternalStorageDirectory().path)
    val blockSize = stat.blockSize.toLong()
    val availableBlocks = stat.availableBlocks.toLong()
    return availableBlocks * blockSize
}

fun getBatteryLevel(batteryIntent: Intent): Float {
    val level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
    val scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
    return level / scale.toFloat()
}

fun getBatteryInfo(batteryIntent: Intent): String {
    val status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
    val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
            || status == BatteryManager.BATTERY_STATUS_FULL
    val chargePlug = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
    val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
    val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

    val level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
    val scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

    val batteryPct = level / scale.toFloat()
    return "Battery Info: isCharging=$isCharging usbCharge=$usbCharge  acCharge=$acCharge  batteryPct=$batteryPct"
}
