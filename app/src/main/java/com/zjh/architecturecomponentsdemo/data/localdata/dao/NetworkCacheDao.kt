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
    /**
     *  If a Single<T> query returns null, Room will throw EmptyResultSetException.
     *  @see <https://developer.android.com/reference/android/arch/persistence/room/Query.html</a>
     */
    @Query("SELECT * FROM t_network_cache where id = :id")
    fun getNetworkCacheDataById(id:Int): Single<NetworkCacheEntity>

    @Insert
    fun insertNetworkCacheData(vararg networkCacheEntity: NetworkCacheEntity)
}