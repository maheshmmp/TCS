package com.example.mynewsapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynewsapplication.dao.NewsDao
import com.example.mynewsapplication.models.Articles
import com.example.mynewsapplication.models.NewsHeadlines
import com.example.mynewsapplication.utils.Convertors


@Database(entities = [NewsHeadlines::class, Articles::class], version = 1,exportSchema = false)
@TypeConverters(Convertors::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}