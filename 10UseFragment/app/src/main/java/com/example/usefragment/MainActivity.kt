package com.example.usefragment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var buttonAdd: Button
    lateinit var buttonRemove: Button
    lateinit var textView: TextView
    var fragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdd = findViewById(R.id.button)
        buttonRemove = findViewById(R.id.button2)
        textView = findViewById(R.id.textView2)



        buttonAdd.setOnClickListener() {
            val name = "Fragment ${++fragmentIndex}"
            val fragment = MyFirstFragment(name)

            textView.text = "一共有${fragmentIndex}个Fragment"

            supportFragmentManager.beginTransaction()
                .addToBackStack(null)//支持Back回退
                .add(R.id.fragmentContainerView, fragment, "b")
                .commit()
        }

        buttonRemove.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag("b")
            if (fragment != null) {
                if (fragmentIndex > 0) {
                    fragmentIndex--
                }
                textView.text = "一共有${fragmentIndex}个Fragment"

                supportFragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit()
            }
        }
    }
}