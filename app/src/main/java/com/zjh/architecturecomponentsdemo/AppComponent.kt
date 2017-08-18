package com.zjh.architecturecomponentsdemo

import com.boildcoffee.imboildcoffee.AppModule
import com.zjh.architecturecomponentsdemo.data.RepositoryModule
import com.zjh.architecturecomponentsdemo.data.localdata.DataModule
import com.zjh.architecturecomponentsdemo.ui.ImageViewModel
import dagger.Component

/**
 * @author zjh
 * 2017/8/18
 */
@Component(modules = arrayOf(AppModule::class, DataModule::class, RepositoryModule::class))
interface AppComponent{
    fun inject(imageViewModel: ImageViewModel)
}