package com.example.fragmenttoactivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ButtonFragment.ResponseToFragmentButtonClick {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        val fragment = ButtonFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }


    override fun responseClick(clickCount: Int) {
        textView.text = clickCount.toString()
    }

}