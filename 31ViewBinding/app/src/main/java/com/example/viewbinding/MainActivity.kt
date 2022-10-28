package com.example.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewbinding.databinding.ActivityMainBinding
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.button.setOnClickListener() {
//            binding.tv.text = "${LocalTime.now()}"
//        }

        with(binding) {
            button.setOnClickListener() {
                tv.text = "${LocalTime.now()}"
            }
        }

    }
}