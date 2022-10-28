package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.db.Database
import com.example.room.entity.MyData

class MyDataRepository(private val db: Database) {
    suspend fun add(myData: MyData) {
        db.myDataDao().add(myData)
    }
    // 每个 Repository 只实例化一个 LiveData，这样外界就可以观察它
    // 使用 lazy 保证只实例化一次
    private val myDataList: LiveData<List<MyData>> by lazy {
        db.myDataDao().getAllReturnLiveData()
    }
    // 暴露获得 LiveData 的方法
    fun getAllMyData(): LiveData<List<MyData>> {
        return myDataList
    }
}