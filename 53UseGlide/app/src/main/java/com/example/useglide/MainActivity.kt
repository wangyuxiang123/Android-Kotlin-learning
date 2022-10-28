package com.example.useglide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.useglide.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private val IMAGE_DIRECTOR = "IMAGE_DIRECTOR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.btnDownload.setOnClickListener() {
            Glide
                .with(this)
                .load(Uri.parse("https://jinxuliang.com/images/image_01.jpg"))
                .into(dataBinding.imageView)
        }
        dataBinding.btnSave.setOnClickListener() {
            Glide
                .with(this)
                .load(Uri.parse("https://jinxuliang.com/images/image_01.jpg"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                // 请求监听器
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(this@MainActivity, "请求失败", Toast.LENGTH_SHORT).show()
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(this@MainActivity, "资源准备完毕", Toast.LENGTH_SHORT).show()
                        resource?.let {
                            val bitmap = resource.toBitmap()
                            val imagePath = saveImageToInternalStorage(bitmap)
                            // /data/user/0/com.al.kotlin01helloworld/app_IMAGE_DIRECTOR/818f416d-f0db-47d8-b731-4695e8034941.jpg
                            Log.d("Glide", imagePath)
                        }
                        return false
                    }
                })
                .into(dataBinding.imageView)
        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): String {
        var file = getDir(IMAGE_DIRECTOR, Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")
        try {
            val stream = FileOutputStream(file)
            stream.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                stream.flush()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file.absolutePath
    }
}
