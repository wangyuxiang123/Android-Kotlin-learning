package com.example.multiltemtype

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val TEXT_VIEW_TYPE = 1
const val IMAGE_VIEW_TYPE = 2
const val UNKNOWN_VIEW_TYPE = -1

class TextViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    fun bind(item: MyData) {
        val tvContent = root as TextView
        if (item is TextData) {
            tvContent.text = item.context
        } else {
            tvContent.text = item.toString()
        }
    }
}

class ImageViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    fun bind(item: MyData) {
        val imageView = root.findViewById<ImageView>(R.id.imageView)
        if (item is ImageData) {
            imageView.setImageResource(item.imgResId)
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }
}