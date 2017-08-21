package com.zjh.architecturecomponentsdemo.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zjh.architecturecomponentsdemo.MyApplication
import com.zjh.architecturecomponentsdemo.data.ImageRepository
import com.zjh.architecturecomponentsdemo.data.bean.ImageBean
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

    val mImageBeanListLiveData:MutableLiveData<MutableList<ImageBean>> = MutableLiveData()

    init {
        (application as MyApplication).appComponent.inject(this)
    }

    fun loadData(param: PagingParam,onError: Consumer<in Throwable>,onComplete:Action = Action {  }){
        mImageRepository.getDatas(param, Consumer {
            if (it.isNotEmpty()){
                mImageBeanListLiveData.value = it.toMutableList()
            }
        },onError,onComplete)
    }

    override fun onCleared() {
        super.onCleared()
        mImageRepository.destroy()
    }


}
