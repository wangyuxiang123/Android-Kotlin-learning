package com.example.retrofit.service

import com.example.retrofit.model.MyData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MyListServer {
    companion object {
        var baseurl = "https://jinxuliang.com/openservice/"
        val server: MyListServer by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(MyListServer::class.java)
        }
    }

    @GET("api/test/mydata/{count}")
    suspend fun getMyDataList(@Path("count") count: Int): Response<List<MyData>>
}