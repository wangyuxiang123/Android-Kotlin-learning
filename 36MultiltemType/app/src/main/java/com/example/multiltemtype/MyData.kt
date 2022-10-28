package com.example.multiltemtype

import android.content.Context

interface MyData

data class TextData(val context: String) : MyData

data class ImageData(val imgResId: Int) : MyData

class DataSource {
    companion object {
        fun getDataList(context: Context): List<MyData> {
            val dataList = mutableListOf<MyData>()
            for (i in 1..2) {
                dataList.add(TextData("图片$i"))
                val imageId = context.resources.getIdentifier(
                    "img$i",
                    "drawable",
                    context.packageName
                )
                dataList.add(ImageData(imageId))
            }
            return dataList
        }
    }
}