package com.example.activitytofragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

const val MESSAGE_KEY = "MESSAGE_KEY"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnUseMethod: Button = findViewById(R.id.buttonUseMethod)
        btnUseMethod.setOnClickListener() {
            val fragment = BlankFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
            // 方式1. 通过 Fragment 方法传递信息
            // fragment.receiveMessage("方式1. 通过 Fragment 方法传递信息")
            fragment.receiveMessage("当前时间\n${Date()}")
        }

        val btnUseArgument = findViewById<Button>(R.id.buttonUseArgument)
        btnUseArgument.setOnClickListener {
            // 方式2. 通过 Bundle 传递信息,适合小app
            val argument = Bundle()
            argument.putString(MESSAGE_KEY, "方式2. 通过 Bundle 传递信息")
            val fragmentOfArg = BlankFragment()
            fragmentOfArg.arguments = argument

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentOfArg)
                .commit()
        }

        // 方式3. Android默认创建Fragment的工厂方法，适合大型app，有很多很多fragment
        val btnUseFactoryMethod = findViewById<Button>(R.id.buttonUseFactoryMethod)
        btnUseFactoryMethod.setOnClickListener() {
            val fragmentFactoryMethodFragment =
                FactoryMethodFragment.newInstance("方式3.", "Android 默认创建 Fragment 的 工厂方法")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentFactoryMethodFragment)
                .commit()
        }
    }
}