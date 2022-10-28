package com.example.testimagineview

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView2)
        button = findViewById(R.id.button2)
        var counter = 0
        button.setOnClickListener {
            counter++
            if (counter % 2 == 0)
                imageView.setImageResource(R.drawable.img2)
            else
                imageView.setImageResource(R.drawable.img1)
        }


    }
}