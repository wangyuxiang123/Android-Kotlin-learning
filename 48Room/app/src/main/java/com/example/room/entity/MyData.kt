package com.example.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mydata")
data class MyData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val info: String
)
