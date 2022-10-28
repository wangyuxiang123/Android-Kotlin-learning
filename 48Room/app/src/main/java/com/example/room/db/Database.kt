package com.example.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.dao.MyDataDao
import com.example.room.entity.MyData


@Database(entities = [MyData::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun myDataDao(): MyDataDao
}