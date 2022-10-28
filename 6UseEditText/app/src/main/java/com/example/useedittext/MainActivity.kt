package com.example.useedittext

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText: EditText = findViewById(R.id.editTextPhone)
        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val strLength = s.toString().length
                if (strLength == 11) {
                    textView.text = "您输入的电话号码是：${s}"
                } else {
                    textView.text = "还剩余${11 - strLength}个数字"
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        button.setOnClickListener {
            val phoneNumber = editText.text.toString().length
            if (phoneNumber != 11) {
                AlertDialog.Builder(this)
                    .setMessage("无效号码！")
                    .setPositiveButton("好") { diag, _ -> diag.dismiss() }
                    .create()
                    .show()
            } else {
                callPhone(editText.text.toString())
            }
        }
    }

    private fun callPhone(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse("tel:${phoneNumber}")
        intent.setData(data)
        startActivity(intent)
    }
}