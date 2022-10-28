package com.example.transformation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tvInfo)
        val infoObserver = Observer<String> {
            textView.text = it
        }
        val myTimer = MyTimer()
        myTimer.currentTimeString.observe(this, infoObserver)
    }
}