package com.example.vp2withfragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    private val imageView = view.findViewById<ImageView>(R.id.imageView)


    fun bind(item: ImageItem) {
        tvTitle.text = item.title
        imageView.setImageResource(item.imageId)
    }
}