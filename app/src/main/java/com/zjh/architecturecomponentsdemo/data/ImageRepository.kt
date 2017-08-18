package com.zjh.architecturecomponentsdemo.data

import com.boildcoffee.imboildcoffee.data.IRepository
import com.zjh.architecturecomponentsdemo.data.enitity.Image
import com.zjh.architecturecomponentsdemo.data.localdata.AppDatabase
import io.reactivex.Flowable

/**
 * @author zjh
 * 2017/8/18
 */
class ImageRepository(val appDatabase: AppDatabase) : IRepository<Image>{
    override fun getData(dataId: Long): Flowable<Image> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDatas(): Flowable<List<Image>> {
        return appDatabase.imageDao().getAllImages()
    }

    override fun insertData(vararg data: Image): List<Image> {
        appDatabase.imageDao().insertImages(*data)
        return data.toList()
    }

    override fun deleteData(data: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteDatas() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}