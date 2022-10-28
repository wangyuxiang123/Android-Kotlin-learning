package com.example.a23navpopupdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import kotlin.system.exitProcess


class EndFragment : Fragment() {
    private lateinit var btnFinish: Button
    private lateinit var btnBackFirstPage: Button
    private lateinit var btnRestartWizard: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_end, container, false)
        btnFinish = root.findViewById(R.id.btnFinish)
        btnBackFirstPage = root.findViewById(R.id.btnBackFirstPage)
        btnRestartWizard = root.findViewById(R.id.btnRestartWizard)

        val navController = findNavController()
        btnFinish.setOnClickListener() {
            exitProcess(0)
        }
        btnBackFirstPage.setOnClickListener() {
            navController.navigate(R.id.action_endFragment_to_homeFragment)
        }
        btnRestartWizard.setOnClickListener() {
            navController.popBackStack(R.id.startFragment, false)
        }

        return root
    }


}