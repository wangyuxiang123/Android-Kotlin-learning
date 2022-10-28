package com.example.navuiintro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ThirdFragment : Fragment() {
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_third, container, false)

        floatingActionButton = root.findViewById(R.id.floatingActionButton3)
        floatingActionButton.setOnClickListener() {
            navController = findNavController()
            navController.navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        return root
    }


}