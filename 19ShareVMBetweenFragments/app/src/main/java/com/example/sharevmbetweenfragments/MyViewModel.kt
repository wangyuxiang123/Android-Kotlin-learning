package com.example.sharevmbetweenfragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score


    fun changeScore(value: Int) {
        _score.postValue(value)
    }


}