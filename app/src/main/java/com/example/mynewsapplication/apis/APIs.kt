package com.example.mynewsapplication.apis

import com.example.mynewsapplication.models.NewsHeadlines
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("v2/top-headlines")
    fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Single<NewsHeadlines>


}