package com.example.traditionretrofit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Insets.add
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets.add
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.traditionretrofit.databinding.ActivityMainBinding
import com.example.traditionretrofit.databinding.ParallelDownloadsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParallelDownloadsActivity : AppCompatActivity() {
    private lateinit var dataBinding: ParallelDownloadsBinding
    private val imageServer: MyImageServer = MyImageServer.imageServer
    private var downloadCounter: Int = 0
    private val images: MutableList<Bitmap> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.parallel_downloads)

        dataBinding.btnLoad.setOnClickListener {
            if (images.size > 0) {
                val index = downloadCounter % images.size
                dataBinding.tvUrl.text = "显示第 ${index + 1} 张图片"
                dataBinding.ivImage.setImageBitmap(images[index])
                downloadCounter++
            } else {
                it.isEnabled = false
                downloadImage()
            }

        }
    }


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


    private fun downloadImage() {
        lifecycleScope.launch {
            val imageCount = getImageItem()
            if (imageCount > 0) {
                repeat(imageCount) {
                    var imageName = ""
                    val index = it + 1
                    if (index < 10) {
                        imageName = "image_0$index.jpg"
                    } else {
                        imageName = "image_$index.jpg"
                    }
//                    myLog("图片：$imageName")

                    val bitmap = async {
                        getImageUseCoroutine(imageName)
                    }.await()
                    bitmap?.let {
                        images.add(it)
                        dataBinding.tvUrl.text = "已下载图片：${++downloadCounter}"
                    }
                }
                dataBinding.tvUrl.text = "图片下载完毕"
                dataBinding.btnLoad.text = "切换显示图片"
                dataBinding.btnLoad.isEnabled = true
            }
        }
    }

}