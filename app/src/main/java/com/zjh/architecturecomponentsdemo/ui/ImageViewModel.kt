package com.zjh.architecturecomponentsdemo.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zjh.architecturecomponentsdemo.MyApplication
import com.zjh.architecturecomponentsdemo.data.ImageRepository
import com.zjh.architecturecomponentsdemo.data.enitity.Image
import io.reactivex.disposables.CompositeDisposable

/**
 * @author zjh
 * 2017/8/18
 */
class ImageViewModel(application: Application) : AndroidViewModel(application){
    private val compositeDisposable = CompositeDisposable()

    lateinit var imageRepository:ImageRepository

    val images: MutableLiveData<MutableList<Image>> = MutableLiveData()

    init {
//        (application as MyApplication).appComponent.inject(this)
    }
}
