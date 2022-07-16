package com.example.mynewsapplication.apis

import com.example.mynewsapplication.models.NewsHeadlines
import com.example.mynewsapplication.utils.Constants
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("v2/top-headlines")
    fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Single<NewsHeadlines>

    @GET("v2/top-headlines")
    suspend fun getNewsHeadlinesCoroutine(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsHeadlines>

}