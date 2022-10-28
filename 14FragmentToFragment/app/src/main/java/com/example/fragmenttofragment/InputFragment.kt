package com.example.fragmenttofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment


class InputFragment : Fragment() {
    lateinit var editText: EditText
    lateinit var button: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_input, container, false)
        editText = root.findViewById(R.id.edtUserInput)
        button = root.findViewById(R.id.btnSend)

        button.setOnClickListener() {
            val message = Bundle()
            message.putString(MESSAGE_KEY, editText.text.toString())
            // 通过 Activity 定义的共有方法中转
            (activity as MainActivity).switchFragment(SHOW_FRAGMENT, message)
        }


        return root
    }


}