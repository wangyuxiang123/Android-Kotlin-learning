package com.example.network

import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

suspend fun changeToBlackWhite(source: Bitmap): Bitmap {
    return withContext(Dispatchers.Default) {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        source.getPixels(pixels, 0, width, 0, 0, width, height)
        var R: Int
        var G: Int
        var B: Int
        for (y in 0 until height) {
            for (x in 0 until width) {
                val index = y * width + x
                val r: Int = pixels[index] shr 16 and 0xff
                val g: Int = pixels[index] shr 8 and 0xff
                val b: Int = pixels[index] and 0xff
                val s = (r + g + b) / 3
                // 图像黑白化
                // 图像黑白化
                R = s
                R = if (R < 0) 0 else if (R > 255) 255 else R
                G = s
                G = if (G < 0) 0 else if (G > 255) 255 else G
                B = s
                B = if (B < 0) 0 else if (B > 255) 255 else B
                pixels[index] = -0x1000000 or (R shl 16) or (G shl 8) or B
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        // pix = null
        try {
            val out = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext bitmap
    }
}