package com.zjh.architecturecomponentsdemo.data.localdata

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.zjh.architecturecomponentsdemo.data.localdata.dao.NetworkCacheDao
import com.zjh.architecturecomponentsdemo.data.localdata.dao.entity.NetworkCacheEntity

/**
 * @author zjh
 * 2017/8/9
 */
@Database(
        entities = arrayOf(NetworkCacheEntity::class),
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun networkCacheDao(): NetworkCacheDao

    companion object {
        private const val DB_NAME = "db.appDatabase"

        fun createInMemoryDatabase(context: Context): AppDatabase
                = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java).build()

        fun createPersistentDatabase(context: Context): AppDatabase
                = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
    }
}