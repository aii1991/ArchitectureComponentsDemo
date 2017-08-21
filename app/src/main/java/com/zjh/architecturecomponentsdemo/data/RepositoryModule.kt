package com.zjh.architecturecomponentsdemo.data

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author zjh
 * 2017/8/18
 */
@Module
class RepositoryModule{
    @Singleton
    @Provides
    fun provideImageRepository() = ImageRepository()
}