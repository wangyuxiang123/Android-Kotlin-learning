package com.example.fragmenttoactivity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class ButtonFragment : Fragment() {
    // 用于引用外部监听器对象
    private var listener: ResponseToFragmentButtonClick? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_button, container, false)
        val btnSend = root.findViewById<Button>(R.id.buttonSend)
        var count = 0

        btnSend.setOnClickListener() {
            listener?.responseClick(++count)
        }


        return root
    }

    interface ResponseToFragmentButtonClick {
        fun responseClick(clickCount: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 这个fragment所附加的activity是否实现了这个接口
        // 将外部 Activity 设置为监听者
        if (context is ResponseToFragmentButtonClick) {
            listener = context
        } else {
            throw RuntimeException("$context 必须实现 ResponseToFragmentButtonClick 接口")
        }
    }

}