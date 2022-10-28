package com.example.fragmentwithviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class BlankFragment : Fragment() {
    lateinit var viewModel: ExampleViewModel
    lateinit var tvInfo: TextView
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_blank, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvInfo = root.findViewById(R.id.tvInfo)
        tvInfo.text = "0"
        viewModel = ViewModelProvider(this).get(ExampleViewModel::class.java)

        val updataCounter = Observer<Int> {
            tvInfo.text = it.toString()
        }
        viewModel.counter.observe(viewLifecycleOwner, updataCounter)
    }


}