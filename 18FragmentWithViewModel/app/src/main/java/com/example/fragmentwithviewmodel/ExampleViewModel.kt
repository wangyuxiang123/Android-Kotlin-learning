package com.example.fragmentwithviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExampleViewModel : ViewModel() {
    // 计数器
    var _counter: MutableLiveData<Int> = MutableLiveData()

    val counter: LiveData<Int> get() = _counter

    fun changeCounterValue(value: Int) {
        _counter.postValue(value)
    }
}