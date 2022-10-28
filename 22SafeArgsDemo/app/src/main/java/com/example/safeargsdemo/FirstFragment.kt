package com.example.safeargsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlin.random.Random


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_first, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.btnSend)
        val tvRandom: TextView = view.findViewById(R.id.tvRandom)

        val ranNumber = Random.nextInt(100)
        tvRandom.text = ranNumber.toString()

        button.setOnClickListener() {
            val navCtrl = findNavController()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(ranNumber)

            navCtrl.navigate(action)
        }
    }
}