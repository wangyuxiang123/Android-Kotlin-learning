package com.example.uselifedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyClass {
    val counter: MutableLiveData<Int> = MutableLiveData(0)

    val counterGet: LiveData<Int> get() = counter

    fun addCount() {
        counter.value = counter.value?.plus(1)
    }
}