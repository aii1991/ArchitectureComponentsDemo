package com.zjh.architecturecomponentsdemo.data.enitity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author zjh
 * 2017/8/18
 */
@Entity(tableName = "t_image")
data class Image(@PrimaryKey @ColumnInfo(name = "id") var id: String,
                 @ColumnInfo(name = "desc") var desc: String,
                 @ColumnInfo(name = "url") var url: String)