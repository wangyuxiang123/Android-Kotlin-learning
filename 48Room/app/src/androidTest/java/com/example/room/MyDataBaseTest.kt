package com.example.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.room.dao.MyDataDao
import com.example.room.db.Database
import com.example.room.helper.DbHelper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyDataBaseTest {
    private lateinit var myDataDao: MyDataDao
    private lateinit var db: Database

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()

        myDataDao = db.myDataDao()
    }


//    @Test
//    fun addTest() {
//        val mydata = DbHelper.createExampleMyData()
//        myDataDao.add(mydata)
//        showLog("插入的数据：$mydata")
//        val objFromDb = mydata.id?.let { myDataDao.findById(it) }
//        showLog("读取的数据：$objFromDb")
//        Assert.assertNotNull(objFromDb)
//    }

    @After
    fun closeDb() {
        db.close()
    }
}