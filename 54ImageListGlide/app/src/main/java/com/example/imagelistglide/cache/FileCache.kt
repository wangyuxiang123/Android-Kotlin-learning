package com.example.imagelistglide.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileCache {
    companion object {
        fun getImage(context: Context, url: String): Bitmap? {
            val fileName = "${context.cacheDir.absolutePath}/${url.hashCode()}"

            return if (File(fileName).exists()) {
                BitmapFactory.decodeStream(FileInputStream(fileName))
            } else {
                null
            }
        }

        // 追加图片，线程安全
        fun putImage(context: Context, url: String, image: Bitmap) {
            val fileName: String = "${context.cacheDir.absolutePath}/${url.hashCode()}"

            FileOutputStream(File(fileName)).use {
                image.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        }
    }
}