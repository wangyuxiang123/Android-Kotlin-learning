package com.example.fragmentstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {
    private lateinit var btnClick: Button
    private lateinit var tvInfo: TextView
    private lateinit var cbSaveSate: CheckBox
    private var counter = 0
    private val key = "counter"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_my, container, false)

        btnClick = root.findViewById(R.id.buttonClick)
        tvInfo = root.findViewById(R.id.textViewInfo)
        cbSaveSate = root.findViewById(R.id.checkBoxSaveState)

        btnClick.setOnClickListener {
            tvInfo.text = "counter: ${++counter}"
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 恢复数据
        if (savedInstanceState != null && savedInstanceState.containsKey(key)) {
            counter = savedInstanceState.getInt(key)
            tvInfo.text = "counter: $counter"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (cbSaveSate.isChecked) {
            outState.putInt(key, counter)
        }
    }
}