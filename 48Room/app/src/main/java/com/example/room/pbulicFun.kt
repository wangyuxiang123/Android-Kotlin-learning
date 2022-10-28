package com.example.room

import android.content.Context
import android.util.Log
import android.widget.Toast

val tag = "MainActivity"

fun showText(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun showLog(text: String) {
    Log.d(tag, text)
}