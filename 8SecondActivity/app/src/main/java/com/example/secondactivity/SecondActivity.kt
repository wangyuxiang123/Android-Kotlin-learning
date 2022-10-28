package com.example.secondactivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView = findViewById(R.id.textView)


        val name = intent.getStringExtra(NAME_KEY)
        val age = intent.getIntExtra(AGE_KEY, 0)

        val user = intent.getParcelableExtra<User>(OBJECT_KEY)

        if (name != null) {
            textView.text = "姓名$name,年龄$age,\nUser(${user!!.name},${user.age})"
        } else {
            textView.text = "这是第二个页面"
        }

    }
}