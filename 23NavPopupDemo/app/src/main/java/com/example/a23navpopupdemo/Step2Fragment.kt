package com.example.a23navpopupdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class Step2Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_step2, container, false)
        val btnStep2 = root.findViewById<Button>(R.id.btnStep2)
        btnStep2.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_step2Fragment_to_endFragment2)
        }


        return root
    }


}