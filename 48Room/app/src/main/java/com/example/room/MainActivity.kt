package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.room.databinding.ActivityMainBinding
import com.example.room.db.Database
import com.example.room.helper.DbHelper
import com.example.room.vm.MainViewModel
import com.example.room.vm.MyViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

private val MainActivity.viewModelProvider: ViewModelProvider
    get() = ViewModelProvider(this)

private val MainActivity.mainViewModel1: MainViewModel
    get() = ViewModelProvider(this).get(MainViewModel::class.java)

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private val db: Database by lazy {
        showLog("Database is created")
        Room.databaseBuilder(this, Database::class.java, "mydata.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val myViewModelFactory = MyViewModelFactory(application)
        mainViewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)
        mainViewModel.myDataList.observe(this) {
            dataBinding.tvInfo.text = it.toString()
        }
        with(dataBinding) {
            btnRefresh.setOnClickListener {
                // useList()
                // useLiveData()
                // collectFlow()
                useRepository()
            }
        }

    }

    private fun useRepository() {
        mainViewModel.addNewData()
        showText(this, "插入数据完成")
    }

    private fun useList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val myDatalist = db.myDataDao().getAll()
            runOnUiThread {
                dataBinding.tvInfo.text = myDatalist.toString()
            }
        }
    }

    private fun useListLive() {
        db.myDataDao().getAllReturnLiveData().observe(this) {
            dataBinding.tvInfo.text = it.toString()
        }
    }

    private fun collectFlow() {
        lifecycleScope.launch {
            db.myDataDao().getAllReturnFlow().collect {
                dataBinding.tvInfo.text = it.toString()
            }
        }
    }


//    private fun addToDB() {
//        thread {
//            val obj = DbHelper.createExampleMyData()
//            db.myDataDao().add(obj)
//            val allData = db.myDataDao().getAll()
//            runOnUiThread {
//                dataBinding.tvInfo.text = "$obj 已插入数据库"
//                dataBinding.tvInfo.append("============================\n")
//                allData.forEach {
//                    dataBinding.tvInfo.append("\n$it\n")
//                }
//            }
//        }
//    }


    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}