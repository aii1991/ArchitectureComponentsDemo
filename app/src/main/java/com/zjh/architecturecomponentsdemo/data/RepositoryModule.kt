package com.zjh.architecturecomponentsdemo.data

import com.zjh.architecturecomponentsdemo.data.localdata.AppDatabase
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
    fun provideImageRepository(appDatabase: AppDatabase) = ImageRepository(appDatabase)
}