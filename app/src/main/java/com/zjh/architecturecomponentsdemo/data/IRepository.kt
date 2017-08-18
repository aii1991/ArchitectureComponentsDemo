package com.boildcoffee.imboildcoffee.data

import io.reactivex.Flowable

/**
 * @author zjh
 * 2017/8/10
 */
interface IRepository<T>{
    /**
     * get data from repository
     *
     * @return data from repository
     */
    fun getData(dataId: Long): Flowable<T>

    /**
     * get all data from repository
     */
    fun getDatas(): Flowable<List<T>>

    /**
     *  insertData to repository
     *  @return insert data
     */
    fun insertData(vararg data: T): List<T>

    /**
     *  from repository delete data
     */
    fun deleteData(data: T)

    /**
     *  from repository clear all data
     */
    fun deleteDatas()
}