package com.zjh.architecturecomponentsdemo

import android.annotation.SuppressLint
import android.app.Application
import com.boildcoffee.imboildcoffee.AppModule

/**
 * @author zjh
 * 2017/8/18
 */
class MyApplication : Application(){
    val appComponent: AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        var instance: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}