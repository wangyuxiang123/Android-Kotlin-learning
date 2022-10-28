package com.example.secondactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReceiveFromActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_from)

        val button: Button = findViewById(R.id.button3)
        val editTextTextPersonName: EditText = findViewById(R.id.editTextTextPersonName)


        button.setOnClickListener() {
            val data = Intent()
            data.putExtra(MESSAGE_KEY, editTextTextPersonName.text.toString())
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

}