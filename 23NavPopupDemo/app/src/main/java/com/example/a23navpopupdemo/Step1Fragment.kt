package com.example.a23navpopupdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController


class Step1Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_step1, container, false)
        val btnStep1 = root.findViewById<Button>(R.id.btnStep1)
        btnStep1.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_step1Fragment_to_step2Fragment)
        }

        return root
    }


}