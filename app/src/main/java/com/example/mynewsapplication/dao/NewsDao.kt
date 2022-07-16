package com.example.mynewsapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mynewsapplication.models.NewsHeadlines


@Dao
interface NewsDao {

    @Insert
    fun insertNews(news: NewsHeadlines)

    @Query("Select * from NewsHeadlines")
    fun getNewsHeadlinesFromDB(): NewsHeadlines
}