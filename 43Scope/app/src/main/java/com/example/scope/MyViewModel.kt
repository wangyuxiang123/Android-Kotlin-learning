package com.example.scope

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MyViewModel : ViewModel() {
    // 当前任务引用
    private var job: Job? = null
    private val _workingInfo = MutableLiveData<String>()
    val workingInfo: LiveData<String> get() = _workingInfo

    private val _state = MutableLiveData<WorkState>(WorkState.IDLE)
    val currentState: LiveData<WorkState> get() = _state

    fun doWork() {
        job = viewModelScope.launch {
            // 主协程做
            for (i in 0..100 step 10) {
                // 外界请求取消任务时，取消任务
                ensureActive()
                _workingInfo.value = process(i)
            }
        }
    }

    private suspend fun process(percent: Int): String {
        return withContext(Dispatchers.IO) {
            _state.postValue(WorkState.WORKING)
            delay(500)
            var info = "协程使用线程${Thread.currentThread().name}\n工作：${percent}%"
            if (percent == 100) {
                info = "工作完成！"
                _state.postValue(WorkState.IDLE)
                job = null
            }
            return@withContext info
        }
    }

    fun cancel() {
        job?.cancel()
        // 取消由 UI 线程发起，所以可以直接操作
        _state.value = WorkState.CANCELED
        job = null
    }
}