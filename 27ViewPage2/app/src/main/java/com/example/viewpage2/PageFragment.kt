package com.example.viewpage2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class PageFragment(val num: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_page, container, false)

        val tvInfo: TextView = root.findViewById(R.id.tvShow)
        tvInfo.text = "第" + num + "页"


        return root
    }


}