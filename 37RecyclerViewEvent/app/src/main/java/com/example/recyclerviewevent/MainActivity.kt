package com.example.recyclerviewevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewevent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MyClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val rvItem = binding.rvItem
        rvItem.layoutManager = LinearLayoutManager(this)
        val myDataAdapter = MyDataAdapter(createItemList(), this)
        binding.rvItem.adapter = myDataAdapter

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

    override fun onClickRow(id: Int) {
        Toast.makeText(this, "onClickRow $id", Toast.LENGTH_SHORT).show()
    }

    override fun onClickButtonInRow(message: String) {
        Toast.makeText(this, "onClickButtonInRow $message", Toast.LENGTH_SHORT).show()
    }
}