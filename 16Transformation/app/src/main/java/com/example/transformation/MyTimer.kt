package com.example.transformation

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.util.*

class MyTimer {
    private val currentTime = MutableLiveData<Long>()

    val currentTimeString: LiveData<String> = Transformations.map(currentTime) {
        DateUtils.formatElapsedTime(it)
    }

    init {
        val timer = Timer()
        val startTime = System.currentTimeMillis()
        var elapsedTime: Long = 0
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                elapsedTime = (System.currentTimeMillis() - startTime) / 1000
                currentTime.postValue(elapsedTime)
            }
        }
        timer.schedule(task, 1000, 1000)
    }
}