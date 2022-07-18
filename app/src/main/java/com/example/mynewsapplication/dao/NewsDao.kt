package com.example.mynewsapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mynewsapplication.models.Articles

@Dao
interface NewsDao {

    @Insert
    fun insertArticle(article: Articles)

    @Query("Select * from Articles")
    fun getSavedArticlesFromDB(): Articles
}