package com.example.usesqliteopenhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.usesqliteopenhelper.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private var dbHelper = MyDBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(dataBinding) {
            tvInfo.text = "数据库版本：${MyDBHelper.VERSION}"

            btnInsert.setOnClickListener {
                MainScope().launch {
                    val db = dbHelper.writableDatabase
                    val insertResult = if (MyDBHelper.VERSION == 1) {
                        dbHelper.insertData(db, "First", 18)
                    } else {
                        dbHelper.insertData2(db, "First", 18, 20)
                    }
                    // db.close()
                    tvInfo.text = insertResult.toString()
                }
            }
            btnShow.setOnClickListener {
                MainScope().launch {
                    val db = dbHelper.readableDatabase
                    val result = dbHelper.query(db)
                    // db.close()
                    tvInfo.text = result
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}