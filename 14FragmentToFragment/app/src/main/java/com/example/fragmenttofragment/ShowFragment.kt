package com.example.fragmenttofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class ShowFragment : Fragment() {
    lateinit var textView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_show, container, false)

        textView = root.findViewById(R.id.tvInfo)

        textView.text = arguments?.getString(MESSAGE_KEY)

        return root
    }

}