package com.example.room.helper

import com.example.room.entity.MyData

class DbHelper {
    companion object {
        var id: Int = 0
        fun createExampleMyData(): MyData {
            return MyData(id++, "First")
        }
    }
}