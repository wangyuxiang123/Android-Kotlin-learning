package com.example.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

suspend fun getUrlString(urlString: String): ByteArray {
    return withContext(Dispatchers.IO) {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        try {
            val out = ByteArrayOutputStream()
            out.use {
                val inStream = connection.inputStream
                inStream.use {
                    if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                        throw IOException(connection.responseMessage)
                    }
                    var bytesRead = 0
                    val buffer = ByteArray(1024)
                    do {
                        bytesRead = inStream.read(buffer)
                        if (bytesRead != -1)
                            out.write(buffer, 0, bytesRead)
                    } while (bytesRead > 0)
                }
                out.toByteArray()
            }
        } finally {
            connection.disconnect()
        }
    }
}

suspend fun getUrlData(url: String): Bitmap? {
    return try {
        val data = getUrlString(url)
        return withContext(Dispatchers.Default) {
            var bitmap: Bitmap? = null
            if (data != null) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            }
            bitmap
        }
    } catch (ex: Exception) {
        null
    }
}