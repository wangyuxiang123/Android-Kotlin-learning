package com.example.safeargsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class SecondFragment : Fragment() {
    private val myArgs by navArgs<SecondFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShow: TextView = view.findViewById(R.id.tvShow)
        tvShow.text = myArgs.number.toString()


        view.findViewById<Button>(R.id.btnBack).setOnClickListener(){
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }


}