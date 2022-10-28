package com.example.recycleviewcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewcrud.databinding.ActivityMainBinding
import java.time.LocalDateTime

class MainActivity : AppCompatActivity(), MyClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DataAdapter
    private var dataList = getDataList()
    private var currentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = DataAdapter(dataList, this)
        binding.tvList.adapter = adapter
        binding.tvList.layoutManager = LinearLayoutManager(this)
        upData(dataList)

        //增
        binding.btnAdd.setOnClickListener {
            val newItem = DataItem(
                "新添加的数据",
                R.mipmap.ic_launcher,
                LocalDateTime.now()
            )
            val size = dataList.size
            dataList.add(newItem)
            dataList.size + 1
            upData(dataList)

            // 通知 Recycle 刷新显示
            adapter.notifyItemInserted(size)
            binding.tvList.scrollToPosition(size)
            Toast.makeText(this, "在末尾添加一行 ${size + 1}", Toast.LENGTH_SHORT)
                .show()

        }

        //删
        binding.btnRemove.setOnClickListener {
            // 未选中
            if (currentIndex == -1) {
                Toast.makeText(this, "未选中", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            dataList.removeAt(currentIndex)
            dataList.size - 1
            upData(dataList)

            adapter.notifyItemRemoved(currentIndex)
            Toast.makeText(this, "删除了第 ${currentIndex + 1} 行数据", Toast.LENGTH_SHORT).show()
            // 标记为 未选中
            currentIndex = -1
        }

        //改
        binding.btnModify.setOnClickListener {
            // 未选中
            if (currentIndex == -1) {
                Toast.makeText(this, "未选中", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newItem = DataItem(
                "修改数据",
                R.mipmap.ic_launcher,
                LocalDateTime.now()
            )
            dataList[currentIndex] = newItem
            adapter.notifyItemChanged(currentIndex)
            Toast.makeText(this, "修改了第 ${currentIndex + 1} 行数据", Toast.LENGTH_SHORT).show()
            // 标记为 未选中
            currentIndex = -1
        }

    }

    override fun onClickRow(index: Int) {
        currentIndex = index
        Toast.makeText(this, "点击了第 ${index + 1} 行数据", Toast.LENGTH_SHORT).show()
    }

    private fun getDataList(): MutableList<DataItem> {
        return mutableListOf(
            DataItem("标题1", R.mipmap.ic_launcher_round, LocalDateTime.now()),
            DataItem("标题2", R.mipmap.ic_launcher_round, LocalDateTime.now()),
            DataItem("标题3", R.mipmap.ic_launcher_round, LocalDateTime.now()),
            DataItem("标题4", R.mipmap.ic_launcher_round, LocalDateTime.now()),
            DataItem("标题5", R.mipmap.ic_launcher_round, LocalDateTime.now())
        )
    }

    private fun upData(dataList: MutableList<DataItem>) {
        binding.textView.text = dataList.size.toString() + "行数据"
    }
}