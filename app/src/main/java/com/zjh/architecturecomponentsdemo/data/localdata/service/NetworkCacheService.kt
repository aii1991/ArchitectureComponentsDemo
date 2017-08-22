package com.zjh.architecturecomponentsdemo.data.localdata.service

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zjh.architecturecomponentsdemo.MyApplication
import com.zjh.architecturecomponentsdemo.data.bean.ImageBean
import com.zjh.architecturecomponentsdemo.data.localdata.dao.entity.NetworkCacheEntity
import com.zjh.architecturecomponentsdemo.rx.CompletableFromAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * @author zjh
 * 2017/8/21
 */
object NetworkCacheService {
    val mGson:Gson = Gson()

    fun insertData(networkCacheEntity: NetworkCacheEntity, onComplete: Action = Action {  }, onError: Consumer<in Throwable> = Consumer {  }): Disposable{
        return CompletableFromAction.create(Action { MyApplication.instance.appDatabase.networkCacheDao().insertNetworkCacheData(networkCacheEntity) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete,onError)
    }

    fun getData(id: Int, onSuccess: Consumer<in List<ImageBean>>, onError: Consumer<in Throwable>): Disposable{
         return MyApplication.instance.appDatabase.networkCacheDao().getNetworkCacheDataById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .map {
                     val resp: List<ImageBean> = mGson.fromJson(it.resp, object :TypeToken<List<ImageBean>>(){}.type)
                     resp
                 }
                .subscribe(onSuccess,onError)
    }


}