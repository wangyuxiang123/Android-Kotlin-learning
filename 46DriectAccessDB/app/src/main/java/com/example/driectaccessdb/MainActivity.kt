package com.example.driectaccessdb

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.driectaccessdb.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(dataBinding) {
            btnCreate.setOnClickListener {
                MainScope().launch {
                    createDBUseSQL()
                }
            }
            btnShow.setOnClickListener {
                MainScope().launch {
                    showDataInDBUseSQL()
                }
            }
        }

    }

    // 使用 SQL 直接创建数据库
    private suspend fun createDBUseSQL() {
        withContext(Dispatchers.IO) {
            val db = openOrCreateDatabase("my_sqlite_db", MODE_PRIVATE, null)
            db.execSQL("DROP TABLE IF EXISTS myTable")
            db.execSQL("CREATE TABLE IF NOT EXISTS myTable(TEXT info,INTEGER value)")
            db.execSQL("INSERT INTO myTable VALUES ('INFO',18)")
            db.execSQL("INSERT INTO myTable VALUES ('这是一个中文信息',200)")
            // 如果 close 了，那么 AS 的 App Inspection 中会无法使用
//            db.close()
        }
        dataBinding.tvInfo.text = "数据库已创建"
    }

    private suspend fun showDataInDBUseSQL() {
        val result = withContext(Dispatchers.IO) {
            val db = openOrCreateDatabase("my_sqlite_db", MODE_PRIVATE, null)
            db.use {
                val query: Cursor = db.rawQuery("SELECT * FROM myTable", null)
                val sb = StringBuilder()
                if (query.moveToFirst()) {
                    do {
                        val info = query.getString(0)
                        val value = query.getInt(1)
                        sb.append("$info $value \n")
                    } while (query.moveToNext())
                } else {
                    sb.append("没有数据")
                }
                query.close()
                sb.toString()
            }
        }
        dataBinding.tvInfo.text = result
    }
}