package com.zjh.architecturecomponentsdemo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.zjh.architecturecomponentsdemo.MyApplication

/**
 * @author zjh
 * 2017/8/21
 */
object NetworkUtils{
    /**
     * check network is enable
     */
    fun isNetworkConnected():Boolean{
        val connectivityManager: ConnectivityManager = MyApplication.instance?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo = connectivityManager.activeNetworkInfo
        return networkInfo.isAvailable
    }
}