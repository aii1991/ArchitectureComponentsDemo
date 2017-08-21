package com.zjh.architecturecomponentsdemo.data.remotedata.service

import com.zjh.architecturecomponentsdemo.data.bean.ImageBean
import com.zjh.architecturecomponentsdemo.data.remotedata.api.ImageApi
import com.zjh.architecturecomponentsdemo.data.remotedata.req.PagingParam
import com.zjh.architecturecomponentsdemo.network.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * @author zjh
 * 2017/8/21
 */
object ImageWebService {
    fun getImages(param:PagingParam, onSuccess: Consumer<in List<ImageBean>>, onError:Consumer<in Throwable>, onComplete: Action= Action {  }): Disposable{
        return RetrofitManager
                .instance
                .createReq(ImageApi::class.java)
                .getImages(param.pageSize,param.currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.results }
                .subscribe(onSuccess,onError,onComplete)
    }
}