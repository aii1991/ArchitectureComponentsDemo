package com.zjh.architecturecomponentsdemo.data

import com.boildcoffee.imboildcoffee.data.IRepository
import com.google.gson.Gson
import com.zjh.architecturecomponentsdemo.data.bean.ImageBean
import com.zjh.architecturecomponentsdemo.data.localdata.dao.entity.NetworkCacheEntity
import com.zjh.architecturecomponentsdemo.data.localdata.service.NetworkCacheService
import com.zjh.architecturecomponentsdemo.data.remotedata.ReqUrl
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
class ImageRepository : IRepository<ImageBean>{
    private val compositeDisposable = CompositeDisposable()
    val mGson: Gson = Gson()

    override fun getData(dataId: Long){
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDatas(param: PagingParam, onSuccess: Consumer<List<ImageBean>>, onError: Consumer<in Throwable>, onComplete: Action) {
        if (NetworkUtils.isNetworkConnected()){
            compositeDisposable.add(
                    ImageWebService.getImages(param,Consumer {
                        compositeDisposable.add(NetworkCacheService.insertData(NetworkCacheEntity(ReqUrl.IMAGE_REQ_URL,param.toString(),mGson.toJson(it))))
                        onSuccess.accept(it)
                    }, onError,onComplete)
            )
        }else{
            compositeDisposable.add(NetworkCacheService.getData(NetworkCacheEntity.getId(ReqUrl.IMAGE_REQ_URL,param.toString()),onSuccess,onError))
        }
    }

    override fun insertData(vararg data: ImageBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteData(data: ImageBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteDatas() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        compositeDisposable.clear()
    }



}