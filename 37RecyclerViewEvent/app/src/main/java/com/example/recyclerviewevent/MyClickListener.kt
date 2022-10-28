package com.example.recyclerviewevent

interface MyClickListener {
    // 获取点击对象 id 值
    fun onClickRow(id: Int)

    // 获取点击对象信息
    fun onClickButtonInRow(message: String)
}