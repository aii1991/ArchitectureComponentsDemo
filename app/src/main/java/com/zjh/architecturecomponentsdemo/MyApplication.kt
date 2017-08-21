package com.zjh.architecturecomponentsdemo

import android.annotation.SuppressLint
import android.app.Application
import com.boildcoffee.imboildcoffee.AppModule
import com.zjh.architecturecomponentsdemo.data.localdata.AppDatabase

/**
 * @author zjh
 * 2017/8/18
 */
class MyApplication : Application(){
    lateinit var appDatabase: AppDatabase

    val appComponent: AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appDatabase = AppDatabase.createPersistentDatabase(this)
    }

    fun getAppDataBase(): AppDatabase{
        return appDatabase
    }

    fun getApplication(): MyApplication{
        return instance
    }


}