package com.example.secondactivity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(var name: String, var age: Int) : Parcelable