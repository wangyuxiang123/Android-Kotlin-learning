package com.example.retrofit.service

import com.example.retrofit.model.LoginInfo
import com.example.retrofit.model.MyClass
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyWebServer {
    companion object {
        var baseurl = "https://jinxuliang.com/MywebApiserver/"
        val server: MyWebServer by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(MyWebServer::class.java)
        }
    }
    // 自动序列化
    @GET("api/myservice")
    fun getValue(): Call<MyClass>

    // 获取原始响应体
    @GET("api/myservice")
    fun getValue2(): Call<ResponseBody>

    // 通用 HTTP
    @HTTP(method = "put", path = "api/myservice", hasBody = false)
    fun putTest(@Query("info") info: String): Call<ResponseBody>

    // 添加 Header
    @Headers("User-Agent:android", "My-Header:Aaron")
    @GET("api/myservice/testHeader")
    fun headerTest(): Call<ResponseBody>

    // 添加动态 Header
    @GET("api/myservice/testHeader")
    fun headerTest2(@Header("token") token: String): Call<ResponseBody>

    // Post
    @POST("api/myservice/login")
    fun login(@Body info: LoginInfo): Call<ResponseBody>
}