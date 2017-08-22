package com.zjh.architecturecomponentsdemo.data.localdata.dao.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author zjh
 * 2017/8/21
 */
@Entity(tableName = "t_network_cache")
data class NetworkCacheEntity(@ColumnInfo(name = "req_rul") var reqUrl:String, var param:String, var resp:String,@PrimaryKey var id:Int = NetworkCacheEntity.getId(reqUrl,param)){
    companion object {
        fun getId(reqUrl: String,param: String): Int{
            return (reqUrl + "_" + param).hashCode()
        }
    }
}