package com.zjh.architecturecomponentsdemo

import com.boildcoffee.imboildcoffee.AppModule
import com.zjh.architecturecomponentsdemo.data.RepositoryModule
import com.zjh.architecturecomponentsdemo.ui.ImageViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * @author zjh
 * 2017/8/18
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,RepositoryModule::class))
interface AppComponent{
    fun inject(imageViewModel: ImageViewModel)
}