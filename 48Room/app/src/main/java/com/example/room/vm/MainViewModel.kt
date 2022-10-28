package com.example.room.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.room.db.Database
import com.example.room.helper.DbHelper
import com.example.room.repository.MyDataRepository
import kotlinx.coroutines.launch

class MainViewModel(val app: Application) : AndroidViewModel(app) {
    // 实例化 Database 对象
    private val db: Database by lazy {
        Room.databaseBuilder(app, Database::class.java, "mydata.db").build()
    }
    private var myDataRepository: MyDataRepository = MyDataRepository(db)

    val myDataList = myDataRepository.getAllMyData()

    fun addNewData() {
        val obj = DbHelper.createExampleMyData()
        // 使用 ViewModel 协程插入数据
        viewModelScope.launch {
            myDataRepository.add(obj)
        }
    }
}