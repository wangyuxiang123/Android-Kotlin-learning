package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val dataAdapter = DataAdapter(createItemList())
        recyclerView.adapter = dataAdapter
    }

    private fun createItemList(): List<MyData> {
        return listOf(
            MyData(10, "First"),
            MyData(20, "First"),
            MyData(30, "First"),
            MyData(40, "First"),
            MyData(50, "First"),
            MyData(60, "First"),
            MyData(70, "First"),
            MyData(70, "First"),
            MyData(70, "First"),
            MyData(70, "First"),
            MyData(70, "First"),
            MyData(70, "First"),
        )
    }
}