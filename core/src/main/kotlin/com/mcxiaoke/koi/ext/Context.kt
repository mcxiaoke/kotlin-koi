package com.mcxiaoke.koi.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.content.*
import android.content.pm.PackageManager
import android.database.Cursor
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
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

inline fun <reified T : View> android.support.v4.app.Fragment.find(id: Int): T = this.view.findViewById(id) as T

private fun inflateView(context: Context, layoutResId: Int, parent: ViewGroup?,
                        attachToRoot: Boolean): View =
        LayoutInflater.from(context).inflate(layoutResId, parent, attachToRoot)

fun Context.inflate(layoutResId: Int): View =
        inflateView(this, layoutResId, null, false)

fun Context.inflate(layoutResId: Int, parent: ViewGroup): View =
        inflate(layoutResId, parent, true)

fun Context.inflate(layoutResId: Int, parent: ViewGroup, attachToRoot: Boolean): View =
        inflateView(this, layoutResId, parent, attachToRoot)

fun Context.smartCacheDir(): File {
    if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
        val aDir = this.externalCacheDir
        val noMedia = File(this.externalCacheDir, Const.FILENAME_NOMEDIA)
        if (!noMedia.exists()) {
            try {
                noMedia.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return aDir
    } else {
        return this.cacheDir
    }
}

private fun Context.getMediaDataColumn(uri: Uri, selection: String?,
                                       selectionArgs: Array<String>?): String {

    var cursor: Cursor? = null
    val column = MediaStore.MediaColumns.DATA
    try {
        cursor = this.contentResolver.query(uri, arrayOf(column),
                selection, selectionArgs,
                null)
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndexOrThrow(column))
    } finally {
        cursor?.close()
    }
}

/**
 * Get a file path from a Uri. This will get the the path for Storage Access
 * Framework Documents, as well as the _data field for the MediaStore and
 * other file-based ContentProviders.
 */
@SuppressLint("NewApi")
fun Context.getRealPath(uri: Uri): String? {

    val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
    // DocumentProvider
    if (isKitKat && DocumentsContract.isDocumentUri(this, uri)) {
        // ExternalStorageProvider
        if (uri.isExternalStorageDocument()) {
            val docId = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val type = split[0]

            if ("primary".equals(type, ignoreCase = true)) {
                return Environment.getExternalStorageDirectory().path + "/" + split[1]
            }
        } else if (uri.isDownloadsDocument()) {

            val id = DocumentsContract.getDocumentId(uri)
            val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/_downloads"), java.lang.Long.valueOf(id)!!)

            return getMediaDataColumn(contentUri, null, null)
        } else if (uri.isMediaDocument()) {
            val docId = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val type = split[0]

            var contentUri: Uri? = null
            if ("image" == type) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            } else if ("video" == type) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            } else if ("audio" == type) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }
            if (contentUri != null) {
                return getMediaDataColumn(contentUri, "_id=?", arrayOf(split[1]))
            }
        }// MediaProvider
        // DownloadsProvider
    } else if (ContentResolver.SCHEME_CONTENT.equals(uri.scheme, ignoreCase = true)) {

        // Return the remote address
        if (uri.isGooglePhotosUri())
            return uri.lastPathSegment

        return getMediaDataColumn(uri, null, null)
    } else if (ContentResolver.SCHEME_FILE.equals(uri.scheme, ignoreCase = true)) {
        return uri.path
    }// File
    // MediaStore (and general)

    return uri.path
}


fun Context.hasCamera(): Boolean {
    val pm = this.packageManager
    return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
            || pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
}

fun Context.mediaScan(uri: Uri) {
    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    intent.setData(uri)
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

