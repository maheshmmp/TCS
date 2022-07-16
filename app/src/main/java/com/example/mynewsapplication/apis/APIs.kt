package com.example.mynewsapplication.apis

import com.example.mynewsapplication.models.NewsHeadlines
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadlinesCoroutine(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsHeadlines>

}