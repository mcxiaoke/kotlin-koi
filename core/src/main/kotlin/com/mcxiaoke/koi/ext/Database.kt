package com.mcxiaoke.koi.ext

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * User: mcxiaoke
 * Date: 16/1/30
 * Time: 00:23
 */

inline fun SQLiteOpenHelper.transaction(action: SQLiteDatabase.() -> Unit) {
    writableDatabase.transaction(action)
}

inline fun SQLiteDatabase.transaction(action: SQLiteDatabase.() -> Unit) {
    beginTransaction()
    try {
        action(this)
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
}