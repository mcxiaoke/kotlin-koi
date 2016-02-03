package com.mcxiaoke.koi.samples

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mcxiaoke.koi.ext.*

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 21:10
 */
// sample data class
data class UserInfo(val name: String, val age: Int, val bio: String?, val hasPet: Boolean)

class CursorExtensionSample(context: Context, name: String,
                            factory: SQLiteDatabase.CursorFactory?, version: Int)
: SQLiteOpenHelper(context, name, factory, version) {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        throw UnsupportedOperationException()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        throw UnsupportedOperationException()
    }

    // available for Cursor
    fun cursorValueExtensions() {
        val cursor = this.writableDatabase.query("table", null, null, null, null, null, null)
        cursor.moveToFirst()
        do {
            val intVal = cursor.intValue("column-a")
            val stringVal = cursor.stringValue("column-b")
            val longVal = cursor.longValue("column-c")
            val booleanValue = cursor.booleanValue("column-d")
            val doubleValue = cursor.doubleValue("column-e")
            val floatValue = cursor.floatValue("column-f")

            // no need to do like this, so verbose
            cursor.getInt(cursor.getColumnIndexOrThrow("column-a"))
            cursor.getString(cursor.getColumnIndexOrThrow("column-b"))
        } while (cursor.moveToNext())
    }

    // available for Cursor
    // transform cursor to model object
    fun cursorToModels() {
        val where = " age>? "
        val whereArgs = arrayOf("20")
        val cursor = this.readableDatabase.query("users", null, where, whereArgs, null, null, null)
        val users1 = cursor.map {
            UserInfo(
                    stringValue("name"),
                    intValue("age"),
                    stringValue("bio"),
                    booleanValue("pet_flag"))
        }

        // or using mapAndClose
        val users2 = cursor.mapAndClose {
            UserInfo(
                    stringValue("name"),
                    intValue("age"),
                    stringValue("bio"),
                    booleanValue("pet_flag"))
        }

        // or using Cursor?mapTo(collection, transform())
    }


    // available for SQLiteDatabase and SQLiteOpenHelper
    // auto apply transaction to db operations
    fun inTransaction() {
        val db = this.writableDatabase
        val values = ContentValues()

        // or db.transaction
        transaction {
            db.execSQL("insert into users (?,?,?) (1,2,3)")
            db.insert("users", null, values)
        }
        // equal to
        db.beginTransaction()
        try {
            db.execSQL("insert into users (?,?,?) (1,2,3)")
            db.insert("users", null, values)
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

}