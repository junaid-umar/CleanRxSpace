package com.android.space.data.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.android.space.data.persistence.dao.BlogDao
import com.android.space.data.persistence.entity.BlogEntity

@Database(entities = [ BlogEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        const val DATABASE_NAME = "space_db"
    }

}