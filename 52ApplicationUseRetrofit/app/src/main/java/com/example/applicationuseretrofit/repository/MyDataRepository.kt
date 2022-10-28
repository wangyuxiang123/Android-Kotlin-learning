package com.example.applicationuseretrofit.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.applicationuseretrofit.api.MyWebServer
import com.example.network.showLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyDataRepository {
    private val TAG = "MyDataRepository"
    private val imageServer: MyWebServer = MyWebServer.server

    // 获取图片总数
    suspend fun getImageCount(): Int {
        return withContext(Dispatchers.IO) {
            val response = imageServer.getImageCount()
            if (response.isSuccessful) {
                response.body()?.imageCount ?: 0
            } else {
                var errMessage = "错误码：${response.code()}"
                response.body()?.let {
                    errMessage += "\n 错误信息：${response.message()}"
                }
                showLog(errMessage)
                throw Exception(errMessage)
            }
        }
    }    // 获取图片总数

    suspend fun getImage(imageName: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val response = imageServer.getImage(imageName)
            if (response.isSuccessful) {
                response.body()?.byteStream().use {
                    BitmapFactory.decodeStream(it)
                }
            } else {
                var errMessage = "错误码：${response.code()}"
                response.body()?.let {
                    errMessage += "\n 错误信息：${response.message()}"
                }
                showLog(errMessage)
                throw Exception(errMessage)
            }
        }
    }
}