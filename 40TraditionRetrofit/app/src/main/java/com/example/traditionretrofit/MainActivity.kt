package com.example.traditionretrofit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.traditionretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private val imageServer: MyImageServer = MyImageServer.imageServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.btnLoad.setOnClickListener {
//            getNewestImageUseCallback()
            getNewestImageUseCoroutine()
        }
    }

    // 传统
    private fun getNewestImageUseCallback() {
        val call = imageServer.getImageCount()
        // Retrofit 采用队列的方式
        call.enqueue(object : Callback<ImageCountResponse> {
            override fun onResponse(
                call: Call<ImageCountResponse>,
                response: Response<ImageCountResponse>
            ) {
                val result = response.body()
                if (result != null) {
                    val imageName = "image_${result.imageCount}.jpg"
                    Toast.makeText(this@MainActivity, "拼接后的图片名称：${imageName}", Toast.LENGTH_SHORT)
                        .show()

                    val call2 = imageServer.getImage(imageName)
                    call2.enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            val stream = response.body()?.byteStream()
                            val bitmap: Bitmap?
                            stream.use {
                                bitmap = BitmapFactory.decodeStream(stream)
                            }
                            runOnUiThread {
                                dataBinding.ivImage.setImageBitmap(bitmap)
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(this@MainActivity, t.message!!, Toast.LENGTH_LONG).show()
                        }
                    })
                }
            }

            override fun onFailure(call: Call<ImageCountResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message!!, Toast.LENGTH_LONG).show()

            }
        })

    }

    //协程
    // 异步任务一：提取图片数量
    private suspend fun getImageItem(): Int {
        // 切换到 IO 线程
        return withContext(Dispatchers.IO) {
            imageServer.getImageCount2().body()?.imageCount ?: 0
        }
    }

    // 异步任务二：下载指定名称图片
    private suspend fun getImageUseCoroutine(imageName: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val result = imageServer.getImage2(imageName)
            var bitmap: Bitmap? = null
            // use 关闭的时候自动关闭流
            result.body()?.byteStream()?.use {
                bitmap = BitmapFactory.decodeStream(it)
            }
            bitmap
        }
    }

    // 组合任务一、二
    private fun getNewestImageUseCoroutine() {
        // Lifecycle 被销毁时，运行的任务会被取消
        lifecycleScope.launch {
            val imageCount = getImageItem()
            if (imageCount != 0) {
                val imageName = "image_$imageCount.jpg"
                Toast.makeText(this@MainActivity, "图片：$imageName", Toast.LENGTH_SHORT)
                    .show()
                dataBinding.tvUrl.text = imageName
                val bitmap = getImageUseCoroutine(imageName)
                dataBinding.ivImage.setImageBitmap(bitmap)
            }
        }
    }
}