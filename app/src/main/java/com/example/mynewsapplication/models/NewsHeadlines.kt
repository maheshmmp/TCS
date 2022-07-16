package com.example.mynewsapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mynewsapplication.models.Articles
import com.example.mynewsapplication.utils.Convertors
import com.google.gson.annotations.SerializedName


@Entity
data class NewsHeadlines(
    @ColumnInfo
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    @ColumnInfo
    var totalResults: Int? = null,


    @SerializedName("articles")
    @ColumnInfo(name = "articles")
    @TypeConverters(Convertors::class)
    var articles: List<Articles>? = null
) {
    @PrimaryKey(autoGenerate = true)
    var newsId: Int = 0
}


