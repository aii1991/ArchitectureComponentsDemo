package com.zjh.architecturecomponentsdemo.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zjh.architecturecomponentsdemo.MyApplication
import com.zjh.architecturecomponentsdemo.data.ImageRepository
import com.zjh.architecturecomponentsdemo.data.enitity.Image
import com.zjh.architecturecomponentsdemo.data.remotedata.req.PagingParam
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * @author zjh
 * 2017/8/18
 */
class ImageViewModel(application: Application) : AndroidViewModel(application){
    @Inject
    lateinit var mImageRepository:ImageRepository

    val mImageLiveData:MutableLiveData<MutableList<Image>> = MutableLiveData()

    init {
        (application as MyApplication).appComponent.inject(this)
    }

    fun loadData(param: PagingParam,onError: Consumer<in Throwable>){
        mImageRepository.getDatas(param, Consumer {
            if (it.isNotEmpty()){
                mImageLiveData.value = it.toMutableList()
            }
        },onError)
    }

    override fun onCleared() {
        super.onCleared()
        mImageRepository.destroy()
    }


}
