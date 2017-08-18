package com.zjh.architecturecomponentsdemo.data.localdata;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author zjh
 * 2017/8/9
 */
@Module
class DataModule{
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context) = AppDatabase.createPersistentDatabase(context)
}