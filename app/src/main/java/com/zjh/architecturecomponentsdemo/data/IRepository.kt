package com.boildcoffee.imboildcoffee.data

import com.zjh.architecturecomponentsdemo.data.remotedata.req.PagingParam
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * @author zjh
 * 2017/8/10
 */
interface IRepository<T>{
    /**
     * get data from repository
     */
    fun getData(dataId: Long)

    /**
     * get all data from repository
     */
    fun getDatas(param: PagingParam,onSuccess: Consumer<List<T>>,onError: Consumer<in Throwable> = Consumer {  },onComplete: Action = Action {  })

    /**
     *  insertData to repository
     */
    fun insertData(vararg data: T)

    /**
     *  from repository delete data
     */
    fun deleteData(data: T)

    /**
     *  from repository clear all data
     */
    fun deleteDatas()

    fun destroy()
}