package com.zjh.architecturecomponentsdemo.data

import android.arch.lifecycle.MutableLiveData
import com.boildcoffee.imboildcoffee.data.IRepository
import com.zjh.architecturecomponentsdemo.data.enitity.Image
import com.zjh.architecturecomponentsdemo.data.localdata.service.ImageCacheService
import com.zjh.architecturecomponentsdemo.data.remotedata.req.PagingParam
import com.zjh.architecturecomponentsdemo.data.remotedata.service.ImageWebService
import com.zjh.architecturecomponentsdemo.util.NetworkUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * @author zjh
 * 2017/8/18
 */
class ImageRepository : IRepository<Image>{
    private val compositeDisposable = CompositeDisposable()

    override fun getData(dataId: Long){
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDatas(param: PagingParam, onSuccess: Consumer<List<Image>>, onError: Consumer<in Throwable>,onComplete: Action) {
        if (NetworkUtils.isNetworkConnected()){
            compositeDisposable.add(
                    ImageWebService.getImages(param,Consumer {
                        compositeDisposable.add(ImageCacheService.insertDatas(it))
                        onSuccess.accept(it)
                    }, onError,onComplete)
            )
        }else{
            compositeDisposable.add(ImageCacheService.getDatas(onSuccess,onError))
        }
    }

    override fun insertData(vararg data: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteData(data: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteDatas() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        compositeDisposable.clear()
    }



}