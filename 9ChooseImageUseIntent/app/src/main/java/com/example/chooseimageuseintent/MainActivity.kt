package com.example.chooseimageuseintent

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var imageView: ImageView

    // 定义activity启动器
    lateinit var luncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        imageView = findViewById(R.id.imageView)

        luncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val bitmap = tryReadBitmap(it.data?.data as Uri)
                bitmap?.let {
                    imageView.setImageBitmap(bitmap)
                }
            }
        }


        button.setOnClickListener() {
            // 创建Intent，通知安卓要求等到一张图片
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            // 让安卓系统帮助创建一个当前可用的图片app选择器界面
            val chooser = Intent.createChooser(intent, "选择一张图片")
            luncher.launch(chooser)
        }
    }

    private fun tryReadBitmap(data: Uri): Bitmap? {
        return try {
            val source = ImageDecoder.createSource(contentResolver, data)
            ImageDecoder.decodeBitmap(source)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }
}