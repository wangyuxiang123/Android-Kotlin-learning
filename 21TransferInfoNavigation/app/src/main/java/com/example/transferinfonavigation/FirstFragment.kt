package com.example.transferinfonavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_first, container, false)

        val btnNav = root.findViewById<Button>(R.id.btnSend)
        val edtUserInput = root.findViewById<EditText>(R.id.etMessage)

        val navController = findNavController()
        btnNav.setOnClickListener {
            val bundle = bundleOf("user_input" to edtUserInput.text.toString())
            navController.navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }


        return root
    }

}