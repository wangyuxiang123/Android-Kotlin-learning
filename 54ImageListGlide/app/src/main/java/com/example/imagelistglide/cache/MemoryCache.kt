package com.example.imagelistglide.cache

import android.graphics.Bitmap

class MemoryCache {
    companion object {
        private val imageCache = HashMap<String, Bitmap>()
        fun getImage(url: String): Bitmap? {
            if (imageCache.containsKey(url)) {
                return imageCache[url]
            }
            return null
        }

        fun putImage(url: String, bitmap: Bitmap) {
            synchronized(MemoryCache::class.java) {
                imageCache[url] = bitmap
            }
        }
    }
}