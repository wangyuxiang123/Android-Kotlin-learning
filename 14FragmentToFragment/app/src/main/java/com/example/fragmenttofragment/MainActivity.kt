package com.example.fragmenttofragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

val INPUT_FRAGMENT = "InputFragment"
val SHOW_FRAGMENT = "ShowFragment"
val MESSAGE_KEY = "message"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(INPUT_FRAGMENT, null)
    }

    fun switchFragment(tag: String, message: Bundle?) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        // 如果Fragment未创建，则实例化它
        if (fragment == null) {
            fragment = when (tag) {
                INPUT_FRAGMENT -> InputFragment()
                else -> ShowFragment()
            }
        }
        message?.apply {
            fragment.arguments = message
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}