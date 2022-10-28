package com.example.applicationuseretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationuseretrofit.model.UiState
import com.example.applicationuseretrofit.repository.MyDataRepository
import com.example.network.showLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {
    private val repo: MyDataRepository = MyDataRepository()

    // 进度
    private val _currentState: MutableLiveData<UiState> = MutableLiveData()
    val currentState: LiveData<UiState> get() = _currentState

    // 异常信息
    private val _info: MutableLiveData<String> = MutableLiveData()
    val info: LiveData<String> get() = _info

    // 获取图片总数，并下载图片
    fun getAllImage(): Flow<ImageInfo> = flow {
        try {
            _currentState.value = UiState.LOADING
            val imageCount = repo.getImageCount()
            showLog("imageCount: $imageCount")
            for (i in 1..imageCount) {
                var imageName = ""
                if (i < 10) {
                    imageName = "image_0$i.jpg"
                } else {
                    imageName = "image_$i.jpg"
                }
                val bitmap = repo.getImage(imageName)

                bitmap?.let {
                    val info = "图片 $imageName 下载完毕"
                    showLog(info)
                    // flow 发送数据
                    emit(ImageInfo(info, it))
                }
            }
            _currentState.value = UiState.IDLE
        } catch (e: Exception) {
            showLog("MainViewModel 异常：$e")
            _currentState.value = UiState.ERROR
            _info.value = e.message
        }
    }
}