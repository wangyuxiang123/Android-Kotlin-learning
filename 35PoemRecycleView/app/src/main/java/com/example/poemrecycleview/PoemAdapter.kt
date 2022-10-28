package com.example.poemrecycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PoemAdapter(private val poems: List<Poem>) : RecyclerView.Adapter<PoemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.poem, parent, false)
        return PoemViewHolder(root)
    }

    override fun onBindViewHolder(holder: PoemViewHolder, position: Int) {
        holder.bind(poems[position])
    }

    override fun getItemCount(): Int {
        return poems.size
    }
}