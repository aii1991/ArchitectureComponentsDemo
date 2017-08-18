package com.boildcoffee.imboildcoffee

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author zjh
 * 2017/8/9
 */
@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context
}