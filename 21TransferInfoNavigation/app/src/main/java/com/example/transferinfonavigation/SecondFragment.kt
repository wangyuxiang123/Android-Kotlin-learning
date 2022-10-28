package com.example.transferinfonavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_second, container, false)

        val text = root.findViewById<TextView>(R.id.tvShow)

        text.text = arguments?.getString("user_input") ?: "无"

        return root
    }
}