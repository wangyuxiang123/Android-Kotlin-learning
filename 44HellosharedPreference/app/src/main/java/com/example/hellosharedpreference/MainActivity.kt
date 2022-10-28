package com.example.hellosharedpreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edtKey: EditText
    private lateinit var edtValue: EditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button
    private lateinit var tvInfo: TextView

    val PREF_FILE_NAME = "mydata"
    val NAME_FILED = "name"
    val AGE_FILED = "age"
    val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtKey = findViewById(R.id.edtKey)
        edtValue = findViewById(R.id.edtValue)
        btnLoad = findViewById(R.id.btnLoad)
        btnSave = findViewById(R.id.btnSave)
        tvInfo = findViewById(R.id.textView)

        btnLoad.setOnClickListener {
            val pref = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            val name = pref.getString(NAME_FILED, "")
            val age = pref.getString(AGE_FILED, "0")
            tvInfo.text = "姓名：$name 年龄：$age"

            // 遍历
            pref.all.forEach {
                Log.d(tag, "${it.key} --> ${it.value}")
            }
        }
        btnSave.setOnClickListener {
            val pref = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            // 1. 获取编辑器对象
            val editor = pref.edit()
            // 2. 写入数据
            editor.putString(NAME_FILED, edtKey.text.toString())
            editor.putString(AGE_FILED, edtValue.text.toString())
            // 3. 提交
            editor.apply()
            tvInfo.text = "数据已经保存"
        }

    }
}