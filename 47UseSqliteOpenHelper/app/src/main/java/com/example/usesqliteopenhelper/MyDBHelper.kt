package com.example.usesqliteopenhelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "mydata.db"

        // 开发新版本时，增加版本号，会自动调用 onUpdate 方法
        private const val DB_VERSION = 2
        val VERSION get() = DB_VERSION

        // 无效的数据库版本
        private const val INVALIDED_DB_VERSION: Long = -1

        private const val TABLE_NAME: String = "myDataClassTable"
        private const val COLUMN_ID: String = "_ID"
        private const val COLUMN_INFORMATION: String = "information"
        private const val COLUMN_MYVALUE: String = "myValue"
        private const val COLUMN_MYVALUE2: String = "myValue2"

        private const val DROP_MYTABLE: String = ("DROP TABLE IF EXISTS $TABLE_NAME")

        private const val CREATE_MYTABLE: String = (
                "CREATE TABLE " + TABLE_NAME +
                        " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_INFORMATION + " TEXT, " +
                        COLUMN_MYVALUE + " INTEGER)"
                )

        private const val CREATE_MYTABLE2: String = (
                "CREATE TABLE " + TABLE_NAME +
                        " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_INFORMATION + " TEXT, " +
                        COLUMN_MYVALUE + " INTEGER ," +
                        COLUMN_MYVALUE2 + " INTEGER)"
                )
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_MYTABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL(DROP_MYTABLE)
        p0.execSQL(CREATE_MYTABLE2)
    }

    // 向 版本1 数据库插入记录
    fun insertData(db: SQLiteDatabase, information: String?, myValue: Int): Long {
        if (db.version != 1) {
            return INVALIDED_DB_VERSION
        }
        val value = ContentValues()
        value.put(COLUMN_INFORMATION, information)
        value.put(COLUMN_MYVALUE, myValue)
        return db.insert(TABLE_NAME, null, value)
    }

    // 向 版本2 插入数据
    fun insertData2(db: SQLiteDatabase, information: String?, myValue: Int, myValue2: Int): Long {
        if (db.version != 2) {
            return INVALIDED_DB_VERSION
        }
        val value = ContentValues()
        value.put(COLUMN_INFORMATION, information)
        value.put(COLUMN_MYVALUE, myValue)
        value.put(COLUMN_MYVALUE2, myValue2)
        return db.insert(TABLE_NAME, null, value)
    }

    fun query(db: SQLiteDatabase): String {
        return when (db.version) {
            1 -> innerQuery(db)
            2 -> innerQuery2(db)
            else -> "不支持的数据库版本"
        }
    }

    // 查询 版本1
    @SuppressLint("Range")
    fun innerQuery(db: SQLiteDatabase): String {
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_INFORMATION, COLUMN_MYVALUE),
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            val sb = StringBuilder()
            if (it.moveToFirst()) {
                do {
                    val info = it.getString(it.getColumnIndex(COLUMN_INFORMATION))
                    val value = it.getInt(it.getColumnIndex(COLUMN_MYVALUE))
                    sb.append("\ninfo:$info \nvalue:$value \n")
                } while (it.moveToNext())
            } else {
                sb.append("未查询到数据,1")
            }
            return sb.toString()
        }
    }

    // 查询 版本2
    @SuppressLint("Range")
    fun innerQuery2(db: SQLiteDatabase): String {
        val cursor = db.query(
            TABLE_NAME,
            // 这里定义列
            arrayOf(COLUMN_INFORMATION, COLUMN_MYVALUE, COLUMN_MYVALUE2),
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            val sb = StringBuilder()
            if (it.moveToFirst()) {
                do {
                    val info = it.getString(it.getColumnIndex(COLUMN_INFORMATION))
                    val value = it.getInt(it.getColumnIndex(COLUMN_MYVALUE))
                    val value2 = it.getInt(it.getColumnIndex(COLUMN_MYVALUE2))
                    sb.append("\ninfo:$info \nvalue:$value \n value2:$value2 \n")
                } while (it.moveToNext())
            } else {
                sb.append("未查询到数据,2")
            }
            return sb.toString()
        }
    }
}