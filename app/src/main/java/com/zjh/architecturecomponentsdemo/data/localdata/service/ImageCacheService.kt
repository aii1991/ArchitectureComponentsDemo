package com.zjh.architecturecomponentsdemo.data.localdata.service

import com.zjh.architecturecomponentsdemo.MyApplication
import com.zjh.architecturecomponentsdemo.data.enitity.Image
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
object ImageCacheService{

    fun insertDatas(images: List<Image>,onComplete: Action = Action {  },onError: Consumer<in Throwable> = Consumer {  }): Disposable{
        return CompletableFromAction.create(Action { MyApplication.instance.appDatabase.imageDao().insertImages(images) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete,onError)
    }

    fun getDatas(onSuccess: Consumer<in List<Image>>,onError: Consumer<in Throwable>): Disposable{
         return MyApplication.instance.appDatabase.imageDao().getAllImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess,onError)
    }


}