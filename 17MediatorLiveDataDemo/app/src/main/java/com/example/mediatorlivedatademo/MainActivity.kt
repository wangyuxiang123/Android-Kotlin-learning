package com.example.mediatorlivedatademo

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    lateinit var edtString1: EditText
    lateinit var edtString2: EditText
    lateinit var tvInfo: TextView
    private val string1 = MutableLiveData<String>()
    private val string2 = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtString1 = findViewById(R.id.edtString1)
        edtString2 = findViewById(R.id.edtString2)
        tvInfo = findViewById(R.id.tvInfo)
        initEditText()
        initMediatorLiveData()

    }

    private fun initEditText() {
        edtString1.addTextChangedListener {
            string1.value = it.toString()
        }
        edtString2.addTextChangedListener {
            string2.value = it.toString()
        }
    }

    private fun initMediatorLiveData() {
        val result = MediatorLiveData<Int>()
        val doSum = Observer<String> {
            val strLength1 = string1.value?.length ?: 0
            val strLength2 = string2.value?.length ?: 0

            result.value = strLength1 + strLength2
        }
        result.addSource(string1, doSum)
        result.addSource(string2, doSum)
        result.observe(this) {
            tvInfo.text = result.value?.toString() ?: "0"
        }
    }
}