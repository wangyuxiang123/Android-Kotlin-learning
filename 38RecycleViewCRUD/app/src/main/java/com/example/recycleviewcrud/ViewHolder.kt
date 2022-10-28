package com.example.recycleviewcrud

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    fun bind(myDataItem: DataItem) {
        val tvTitle = root.findViewById<TextView>(R.id.tvTitle)
        val tvDate = root.findViewById<TextView>(R.id.tvDate)
        val tvImage = root.findViewById<ImageView>(R.id.ivImage)

        tvTitle.text = myDataItem.title
        tvDate.text = myDataItem.date.toString()
        tvImage.setImageResource(myDataItem.imgId)
    }
}
