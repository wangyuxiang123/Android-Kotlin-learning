package com.example.viewpage2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

val NUM = 5

class PageAdpater(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return NUM
    }

    override fun createFragment(position: Int): Fragment {
        return PageFragment(position)
    }
}