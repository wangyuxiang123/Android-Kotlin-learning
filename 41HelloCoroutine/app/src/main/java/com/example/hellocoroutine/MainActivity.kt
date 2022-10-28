package com.example.hellocoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        MainScope().launch {
            delay(1000)
            Log.d("Coroutine", "主进程：${Thread.currentThread().name}")
        }

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        button.setOnClickListener() {
            MainScope().launch {
                val result = progress()
                textView.text = result
            }
        }
    }

    // 推荐写法
    private suspend fun progress(): String {
        return withContext(Dispatchers.IO) {
            "\nCoroutine使用线程\n${Thread.currentThread().name}工作"
        }
    }
}