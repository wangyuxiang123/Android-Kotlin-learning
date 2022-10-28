package com.example.callbackdemo

class myCallBackClass : MyCallback {
    override fun callbackFunc() {
        println("myClass is working....")
    }
}

interface MyCallback {
    fun callbackFunc()
}

class Workder(val callbackObj: MyCallback) {
    fun process() {
        println("doing working.....")
        callbackObj.callbackFunc()
    }

}