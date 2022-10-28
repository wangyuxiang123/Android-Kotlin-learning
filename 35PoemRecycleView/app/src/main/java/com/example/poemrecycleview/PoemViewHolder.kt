package com.example.poemrecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PoemViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    private val tvTitle: TextView = root.findViewById(R.id.tvTitle)
    private val tvAuthor: TextView = root.findViewById(R.id.tvAuthor)
    private val lineContainer: LinearLayout = root.findViewById(R.id.lineContainer)

    fun bind(poem: Poem) {
        tvTitle.text = poem.title
        tvAuthor.text = poem.author

        // 移除原来行
        lineContainer.removeAllViews()
        // 实例化控件
        val inflater = LayoutInflater.from(root.context)

        poem.contents.forEach {
            val textView = inflater.inflate(
                R.layout.poem_line,
                root as ViewGroup,
                false
            )
            (textView as TextView).text = it
            lineContainer.addView(textView)
        }
    }
}