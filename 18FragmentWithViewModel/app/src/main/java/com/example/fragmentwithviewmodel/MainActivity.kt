package com.example.fragmentwithviewmodel

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// 此实例展示Activity如何通过修改Fragment里面的ViewModel来让Fragment里面的界面刷新

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ExampleViewModel
    lateinit var btnClick: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnClick = findViewById(R.id.btnAdd)
        btnClick.setOnClickListener() {
            val currentCount = viewModel.counter.value ?: 0
            viewModel.changeCounterValue(currentCount + 1)
        }
        val blankFragment = BlankFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, blankFragment, "blank_fragment")
            .commit()
    }

    override fun onStart() {
        super.onStart()
        // 注意要在 onStart 才可以找到对应 Fragment
        // 在xml 中定义 id 和 tag 似乎不太管用
        val fragment = supportFragmentManager.findFragmentByTag("blank_fragment")
        viewModel = (fragment as BlankFragment).viewModel
    }
}