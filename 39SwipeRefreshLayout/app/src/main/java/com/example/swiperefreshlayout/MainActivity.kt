package com.example.swiperefreshlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.swiperefreshlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(dataBinding) {
            refreshLayout.setOnRefreshListener {
                tvInfo.text = "正在刷新"
                btnRefresh.isEnabled = true
            }

            btnRefresh.setOnClickListener {
                tvInfo.text = "下拉刷新"
                refreshLayout.isRefreshing = false
                btnRefresh.isEnabled = false
            }
        }
    }
}