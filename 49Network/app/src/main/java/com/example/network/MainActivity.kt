package com.example.network


import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.network.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var a = 0
        dataBinding.btnRefresh.setOnClickListener() {
//            when (a) {
//                0 -> getString()
//                1 -> getImage()
//            }
//            a = (a + 1) % 2
            getImageCoroutines()
        }
        dataBinding.btnTransition.setOnClickListener() {
            getWhiteImageCoroutines()
        }
//        checkNetworkState()
    }

    private fun getWhiteImageCoroutines() {
        val url = "https://jinxuliang.com/openservice/api/imageservice/image_23.jpg"
        MainScope().launch {
            val image = getUrlData(url)
            if (image != null) {
                val blackWhiteImage = changeToBlackWhite(image)
                dataBinding.imageView.setImageBitmap(blackWhiteImage)
            } else {
                dataBinding.tvInfo.text = "图片下载失败"
            }
        }
    }

    private fun getImageCoroutines() {
        val url = "https://jinxuliang.com/openservice/api/imageservice/image_23.jpg"
        MainScope().launch {
            val image = getUrlData(url)
            if (image != null) {
                dataBinding.imageView.setImageBitmap(image)
            } else {
                dataBinding.tvInfo.text = "图片下载失败"
            }
        }
    }

//    private fun getString() {
//        dataBinding.tvInfo.text = "刷新ing"
//        thread {
//            val result = getUrlString("https://www.baidu.com")
//            runOnUiThread {
//                dataBinding.tvInfo.text = "收到字节：${result.size} \n ${String(result)}"
//            }
//        }
//    }
//
//    private fun getImage() {
//        dataBinding.tvInfo.text = ""
//        val url = "https://jinxuliang.com/openservice/api/imageservice/image_22.jpg"
//        thread {
//            val image = getUrlData(url)
//            runOnUiThread {
//                if (image == null) {
//                    dataBinding.tvInfo.text = "图片下载失败"
//                } else {
//                    dataBinding.imageView.setImageBitmap(image)
//                }
//            }
//        }
//    }

    val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            runOnUiThread() {
                dataBinding.tvInfo.text = ("onAvailable:网络已连接")
            }
            showLog("onAvailable:网络已连接")
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            runOnUiThread() {
                dataBinding.tvInfo.text = ("onAvailable:网络已断开")
            }
            showLog("onAvailable:网络已断开")
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val info =
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    "onCapabilitiesChanged:WIFI"
                } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    "onCapabilitiesChanged:CELLULAR"
                } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    "onCapabilitiesChanged:VPN"
                } else {
                    "onCapabilitiesChanged:OTHERS"
                }
            showLog(info)
        }

    }

    private fun checkNetworkState() {
        val connMgr = getSystemService(ConnectivityManager::class.java)
        val request = NetworkRequest.Builder().build()
        connMgr?.registerNetworkCallback(request, callback)
        showLog("网络监听状态已启动")
    }

}