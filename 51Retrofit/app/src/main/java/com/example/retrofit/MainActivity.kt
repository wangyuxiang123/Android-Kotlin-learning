package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.LoginInfo
import com.example.retrofit.model.MyClass
import com.example.retrofit.model.MyData
import com.example.retrofit.service.MyListServer
import com.example.retrofit.service.MyWebServer
import com.example.retrofit.service.MyWebServer.Companion.server
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private val service: MyWebServer = server
    private val listServer: MyListServer = MyListServer.server

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(dataBinding) {
            btnGetValue.setOnClickListener {
                testGetValue()
            }
            btnGetValue2.setOnClickListener {
                testGetValue2()
            }
            btnPut.setOnClickListener {
                testPut()
            }
            btnHeader.setOnClickListener {
                headerTest()
            }
            btnHeader2.setOnClickListener {
                headerTest2()
            }
            btnLogin.setOnClickListener {
                testLogin()
            }
            btnRoutine.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = withContext(Dispatchers.IO) {
                        testRoutine()
                    }
                    dataBinding.tvInfo.text = result.toString()
                }
            }
        }
    }

    private suspend fun testRoutine(): List<MyData> {
        val response = listServer.getMyDataList(5)
        return response.body() ?: emptyList()
    }

    private fun testGetValue() {
        thread {
            val result: Call<MyClass> = service.getValue()
            try {
                val response = result.execute()
                val body = response.body()
                showInfo("${body?.id.toString()} : ${body?.info}")
            } catch (e: Exception) {
                showInfo(e.message)
            }
        }
    }

    private fun testGetValue2() {
        val result = server.getValue2()
        thread {
            try {
                val response = result.execute()
                showServerResponse(response)
            } catch (e: Exception) {
                showInfo(e.message)
            }
        }
    }

    private fun testPut() {
        val result = server.putTest("ABC")
        thread {
            try {
                val response = result.execute()
                showServerResponse(response)
            } catch (e: Exception) {
                showInfo(e.message)
            }
        }
    }

    private fun headerTest() {
        val result = server.headerTest()
        thread {
            try {
                val response = result.execute()
                showServerResponse(response)
            } catch (e: Exception) {
                showInfo(e.message)
            }
        }
    }

    private fun headerTest2() {
        val result = server.headerTest2("Token:${Date().time}")
        thread {
            try {
                val response = result.execute()
                showServerResponse(response)
            } catch (e: Exception) {
                showInfo(e.message)
            }
        }
    }

    private fun testLogin() {
        val result = server.login(LoginInfo("JinXuLiang", "12345678"))
        thread {
            try {
                val response = result.execute()
                showServerResponse(response)
            } catch (e: Exception) {
                showInfo(e.message)
            }
        }
    }

    private fun showServerResponse(response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            showInfo(response.body()!!.string())
        } else {
            showInfo("出错了，状态码：${response.code()}\n${response.errorBody()}")
        }
    }


    private fun showInfo(message: String?) {
        runOnUiThread {
            dataBinding.tvInfo.text = message
        }
    }
}