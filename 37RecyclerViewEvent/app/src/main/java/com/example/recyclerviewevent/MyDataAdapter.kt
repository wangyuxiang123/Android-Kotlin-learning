package com.example.recyclerviewevent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyDataAdapter(private val data: List<MyData>, val listener: MyClickListener?) :
    RecyclerView.Adapter<MyDataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataViewHolder {
        return MyDataViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: MyDataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class MyDataViewHolder private constructor(itemView: View, private val listener: MyClickListener?) :
    RecyclerView.ViewHolder(itemView) {
    private val tvInfo: TextView = itemView.findViewById(R.id.tvInfo)
    private val tvValue: TextView = itemView.findViewById(R.id.tvValue)
    private val btnClick: Button = itemView.findViewById(R.id.btnClick)

    fun bind(item: MyData) {
        tvInfo.text = item.info
        tvValue.text = item.value.toString()
        if (item.value < 60) {
            tvValue.setTextColor(Color.RED)
        } else {
            tvValue.setTextColor(Color.BLUE)
        }
        if (listener != null) {
            itemView.setOnClickListener() {
                listener.onClickRow(item.value)
            }
            btnClick.setOnClickListener {
                listener.onClickButtonInRow(item.info)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: MyClickListener?): MyDataViewHolder {
            val layoutInflate = LayoutInflater.from(parent.context)
            val root = layoutInflate.inflate(R.layout.list_item, parent, false)
            return MyDataViewHolder(root, listener)
        }
    }
}