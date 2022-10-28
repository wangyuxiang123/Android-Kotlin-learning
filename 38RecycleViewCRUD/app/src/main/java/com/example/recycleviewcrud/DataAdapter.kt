package com.example.recycleviewcrud

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val dataList: List<DataItem>, private val listener: MyClickListener) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.list_item,
                parent,
                false
            )
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            listener.onClickRow(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}