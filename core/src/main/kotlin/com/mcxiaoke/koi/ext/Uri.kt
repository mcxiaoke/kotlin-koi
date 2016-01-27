package com.mcxiaoke.koi.ext

import android.net.Uri

/**
 * User: mcxiaoke
 * Date: 16/1/22
 * Time: 13:10
 */

fun Uri.isExternalStorageDocument(): Boolean {
    return "com.android.externalstorage.documents" == this.authority
}

fun Uri.isDownloadsDocument(): Boolean {
    return "com.android.providers.downloads.documents" == this.authority
}

fun Uri.isMediaDocument(): Boolean {
    return "com.android.providers.media.documents" == this.authority
}

fun Uri.isGooglePhotosUri(): Boolean {
    return "com.google.android.apps.photos.content" == this.authority
}
