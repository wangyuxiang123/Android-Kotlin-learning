package com.example.traditionretrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface MyImageServer {
    @GET("api/imageservice/count")
    fun getImageCount(): Call<ImageCountResponse>

    @Streaming
    @GET("api/imageservice/{imageName}")
    fun getImage(@Path("imageName") imageName: String): Call<ResponseBody>

    @GET("api/imageservice/count")
    suspend fun getImageCount2(): Response<ImageCountResponse>

    @Streaming
    @GET("api/imageservice/{imageName}")
    suspend fun getImage2(@Path("imageName") imageName: String): Response<ResponseBody>

    companion object {
        private const val baseUrl = "https://jinxuliang.com/openservice/"
        val imageServer: MyImageServer by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(MyImageServer::class.java)
        }
    }
}