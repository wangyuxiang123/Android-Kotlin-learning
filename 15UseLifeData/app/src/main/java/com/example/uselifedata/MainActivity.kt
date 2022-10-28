package com.example.uselifedata

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    private lateinit var tvCount: TextView
    private lateinit var btnAdd: Button
    private val myClass = MyClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        tvCount = findViewById(R.id.tvCount)

        btnAdd.setOnClickListener() {
            myClass.addCount()
        }

        myClass.counterGet.observe(this) {
            tvCount.text = it.toString()
        }
    }

}