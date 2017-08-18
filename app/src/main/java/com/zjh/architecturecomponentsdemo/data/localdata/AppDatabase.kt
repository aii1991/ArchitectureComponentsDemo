package com.zjh.architecturecomponentsdemo.data.localdata

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.zjh.architecturecomponentsdemo.data.enitity.Image
import com.zjh.architecturecomponentsdemo.data.localdata.dao.ImageDao

/**
 * @author zjh
 * 2017/8/9
 */
@Database(
        entities = arrayOf(Image::class),
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun imageDao(): ImageDao

    companion object {
        private const val DB_NAME = "bc_im.appDatabase"

        fun createInMemoryDatabase(context: Context): AppDatabase
                = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java).build()

        fun createPersistentDatabase(context: Context): AppDatabase
                = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
    }
}