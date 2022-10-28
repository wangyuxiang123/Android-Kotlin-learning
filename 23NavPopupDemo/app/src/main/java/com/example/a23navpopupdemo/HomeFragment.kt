package com.example.a23navpopupdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlin.system.exitProcess

class HomeFragment : Fragment() {
    private lateinit var btnStartWizard: Button
    private lateinit var btnExit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        btnStartWizard = root.findViewById(R.id.btnRunWizard)
        btnExit = root.findViewById(R.id.BtnExit)

        val navController = findNavController()
        btnStartWizard.setOnClickListener() {
            navController.navigate(R.id.action_homeFragment_to_startFragment)
        }
        btnExit.setOnClickListener {
            exitProcess(0)
        }


        return root

    }


}