package com.example.logdemo

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime


class MainActivity : AppCompatActivity() {
    private lateinit var toastbutton: Button
    private lateinit var diaglogbutton: Button
    private lateinit var textView: TextView
    private lateinit var colorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toastbutton = findViewById(R.id.button)
        diaglogbutton = findViewById(R.id.button2)
        textView = findViewById(R.id.textView)
        colorButton = findViewById(R.id.button3)

        logPrint("这是一条日志")

        toastbutton.setOnClickListener {
            val text = "这是一条短信" + LocalTime.now()
            toastShow(this, text)
        }
        toastbutton.setOnLongClickListener {
            val text = "长按了按钮"
            toastShow(this, text)
            true
        }

        diaglogbutton.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("弹框")
                .setPositiveButton("确定") { _, which -> textView.text = "OK" }
                .setNegativeButton("取消") { _, which -> textView.text = "NO" }
                .create()
                .show()
        }

        var counter = 0
        colorButton.setOnClickListener {
            counter++
            if (counter % 2 == 0)
                (it as Button).setTextColor(Color.RED)
            else
                (it as Button).setTextColor(Color.GREEN)
        }
    }
}

