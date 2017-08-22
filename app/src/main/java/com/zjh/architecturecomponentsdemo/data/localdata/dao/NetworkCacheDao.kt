package com.zjh.architecturecomponentsdemo.data.localdata.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.zjh.architecturecomponentsdemo.data.localdata.dao.entity.NetworkCacheEntity
import io.reactivex.Single

/**
 * @author zjh
 * 2017/8/18
 */
@Dao
interface NetworkCacheDao {
    @Query("SELECT * FROM t_network_cache where id = :id")
    fun getNetworkCacheDataById(id:Int): Single<NetworkCacheEntity>

    @Insert
    fun insertNetworkCacheData(vararg networkCacheEntity: NetworkCacheEntity)
}