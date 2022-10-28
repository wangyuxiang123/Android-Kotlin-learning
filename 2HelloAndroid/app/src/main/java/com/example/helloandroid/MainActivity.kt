package com.example.helloandroid

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        var count = 0
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            //都一样
//            textView.setText(count++.toString())
//            textView.text = count++.toString()
            textView.text = "i am click " + count++

        }

    }
}