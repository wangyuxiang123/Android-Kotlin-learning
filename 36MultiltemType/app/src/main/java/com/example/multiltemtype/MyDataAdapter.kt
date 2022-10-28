package com.example.multiltemtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyDataAdapter(private val dataList: List<MyData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val textVH = TextViewHolder(inflater.inflate(R.layout.text_item, parent, false))
        val imageVH = ImageViewHolder(inflater.inflate(R.layout.image_item, parent, false))

        val vh = when (viewType) {
            TEXT_VIEW_TYPE -> textVH
            IMAGE_VIEW_TYPE -> imageVH
            else -> textVH
        }
        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        if (holder is TextViewHolder) {
            holder.bind(item)
        } else if (holder is ImageViewHolder) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataList[position]
        if (item is TextData) {
            return TEXT_VIEW_TYPE
        } else if (item is ImageData) {
            return IMAGE_VIEW_TYPE
        }
        return UNKNOWN_VIEW_TYPE
    }

}