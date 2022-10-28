package com.example.materialtabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var tab: TabLayout
    private lateinit var viewPager2: ViewPager2

    val fragmentList = mutableListOf<Fragment>(
        OneFragment(), TwoFragment(), ThreeFragment()
    )
    val titleList = mutableListOf<String>("One", "Two", "Three")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tab = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.vp)

        val adapter = Adapter(this, fragmentList, titleList)

        viewPager2.adapter = adapter
        TabLayoutMediator(tab, viewPager2).attach()

    }
}