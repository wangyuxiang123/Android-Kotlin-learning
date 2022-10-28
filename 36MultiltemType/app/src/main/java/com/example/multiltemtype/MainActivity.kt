package com.example.multiltemtype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiltemtype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val myDataAdapter = MyDataAdapter(DataSource.getDataList(this))
        dataBinding.rv.adapter = myDataAdapter
        dataBinding.rv.layoutManager = LinearLayoutManager(this)
    }
}