package com.sebastiancorradi.myfriend.datasource.repository.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sebastiancorradi.myfriend.MyFriendApplication
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.datasource.repository.db.converters.StringListConverters
import com.sebastiancorradi.myfriend.datasource.repository.db.dao.CatDao

@TypeConverters( StringListConverters::class )
@Database(entities = [Cat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        private val db = Room.databaseBuilder(
            MyFriendApplication.applicationContext(), AppDatabase::class.java, "cat_database"
        ).build()

        fun getDb():AppDatabase{
            return db
        }
    }


}