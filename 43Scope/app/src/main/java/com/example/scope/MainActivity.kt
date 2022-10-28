package com.example.scope

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.scope.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        with(binding) {
            btnRun.setOnClickListener {
                if (viewModel.currentState.value != WorkState.WORKING) {
                    viewModel.doWork()
                } else {
                    viewModel.cancel()
                }
            }
        }

        viewModel.workingInfo.observe(this) {
            binding.tvInfo.text = it
        }

        viewModel.currentState.observe(this) {
            when (it) {
                WorkState.WORKING -> {
                    binding.pgWorkingr.visibility = View.VISIBLE
                    binding.btnRun.text = "取消"

                }

                WorkState.IDLE -> {
                    binding.pgWorkingr.visibility = View.INVISIBLE
                    binding.btnRun.text = "运行"
                }

                WorkState.CANCELED -> {
                    binding.pgWorkingr.visibility = View.INVISIBLE
                    binding.tvInfo.text = "用户取消工作！"
                    binding.btnRun.text = "重新运行"
                }
            }
        }

    }


}