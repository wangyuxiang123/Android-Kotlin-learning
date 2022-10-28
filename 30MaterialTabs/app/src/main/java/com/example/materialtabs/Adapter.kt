package com.example.materialtabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.ListFragment
import androidx.viewpager.widget.PagerTabStrip
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

val NUM = 3

class Adapter(
    fa: FragmentActivity,
    private val fragmentList: List<Fragment>,
    private val CharSequence: List<String>
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }




}