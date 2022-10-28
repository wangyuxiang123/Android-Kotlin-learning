package com.example.onlytable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tab: TabLayout
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tab = findViewById(R.id.tab)
        textView = findViewById(R.id.textView)

        val badge0: BadgeDrawable = tab.getTabAt(0)!!.orCreateBadge
        badge0.number = 99
        badge0.isVisible = true

        val badge1: BadgeDrawable = tab.getTabAt(2)!!.orCreateBadge
        badge1.number = 9
        badge1.isVisible = true

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                textView.text = "${tab!!.text}被选中"
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                textView.text = "${tab!!.text}被再次选中"
            }

        })


    }
}