package com.example.logdemo

import android.content.Context
import android.util.Log
import android.widget.Toast

//Any == objetc(java)
//?可能为空，不加就是一定不为空
fun logPrint(info: Any?) {
    Log.d("main", info.toString())
}

fun toastShow(context: Context, info: String) {
    Toast.makeText(context, info, Toast.LENGTH_SHORT)
        .show()
}

