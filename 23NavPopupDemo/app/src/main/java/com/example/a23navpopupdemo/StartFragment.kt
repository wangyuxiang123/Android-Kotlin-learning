package com.example.a23navpopupdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment() {
    private lateinit var btnStart: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_start, container, false)

        btnStart = root.findViewById(R.id.btnStart)
        btnStart.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_startFragment_to_step1Fragment)
        }


        return root
    }


}