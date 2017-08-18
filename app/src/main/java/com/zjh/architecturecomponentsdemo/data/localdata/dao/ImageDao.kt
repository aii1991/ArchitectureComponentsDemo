package com.zjh.architecturecomponentsdemo.data.localdata.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.zjh.architecturecomponentsdemo.data.enitity.Image
import io.reactivex.Flowable

/**
 * @author zjh
 * 2017/8/18
 */
@Dao
interface ImageDao{
    @Query("SELECT * FROM t_image")
    fun getAllImages(): Flowable<List<Image>>

    @Insert
    fun insertImages(vararg image: Image)
}