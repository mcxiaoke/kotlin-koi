package com.mcxiaoke.koi.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.app.Notification
import android.content.*
import android.content.pm.PackageManager
import android.database.Cursor
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v4.app.NotificationCompat
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mcxiaoke.koi.Const
import java.io.File
import java.io.IOException

/**
 * User: mcxiaoke
 * Date: 16/1/22
 * Time: 13:09
 */


val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

val Context.displayMetrics: DisplayMetrics
    get() = resources.displayMetrics


fun Context.dpToPx(dp: Int): Int {
    return (dp * this.displayMetrics.density + 0.5).toInt()
}

fun Context.pxToDp(px: Int): Int {
    return (px / this.displayMetrics.density + 0.5).toInt()
}

inline fun <reified T : View> View.find(id: Int): T = this.findViewById(id) as T

inline fun <reified T : View> Activity.find(id: Int): T = this.findViewById(id) as T

inline fun <reified T : View> Fragment.find(id: Int): T = this.view.findViewById(id) as T

private fun inflateView(context: Context, layoutResId: Int, parent: ViewGroup?,
                        attachToRoot: Boolean): View =
        LayoutInflater.from(context).inflate(layoutResId, parent, attachToRoot)

fun Context.inflate(layoutResId: Int): View =
        inflateView(this, layoutResId, null, false)

fun Context.inflate(layoutResId: Int, parent: ViewGroup): View =
        inflate(layoutResId, parent, true)

fun Context.inflate(layoutResId: Int, parent: ViewGroup, attachToRoot: Boolean): View =
        inflateView(this, layoutResId, parent, attachToRoot)

fun Context.hasCamera(): Boolean {
    val pm = this.packageManager
    return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
            || pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
}

fun Context.mediaScan(uri: Uri) {
    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    intent.data = uri
    this.sendBroadcast(intent)
}

// another media scan way
fun Context.addToMediaStore(file: File) {
    val path = arrayOf(file.path)
    MediaScannerConnection.scanFile(this, path, null, null)
}

fun Context.getBatteryStatus(): Intent {
    val appContext = this.applicationContext
    return appContext.registerReceiver(null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED))
}

fun Context.getResourceValue(resId: Int): Int {
    val value = TypedValue()
    this.resources.getValue(resId, value, true)
    return TypedValue.complexToFloat(value.data).toInt()
}

inline fun Context.newNotification(func: NotificationCompat.Builder.() -> Unit): Notification {
    val builder = NotificationCompat.Builder(this)
    builder.func()
    return builder.build()
}

