package com.example.vp2withfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager: ViewPager2 = findViewById(R.id.viewPager)
        pager.adapter = ViewPagerAdapter(currect())
    }

    private fun currect(): List<ImageItem> {
        return listOf(
            ImageItem("image1", R.drawable.image1),
            ImageItem("image2", R.drawable.image2),
            ImageItem("image3", R.drawable.image3),
            ImageItem("image4", R.drawable.image4),
            ImageItem("image5", R.drawable.image5),
            ImageItem("image6", R.drawable.image6),
            ImageItem("image7", R.drawable.image7)
        )
    }
}