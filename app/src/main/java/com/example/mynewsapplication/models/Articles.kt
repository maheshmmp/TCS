package com.example.mynewsapplication.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Articles(

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("title")
    var title: String? = null,


    @SerializedName("description")
    var description: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @SerializedName("content")
    var content: String? = null

):Serializable {
    @PrimaryKey(autoGenerate = true)
    var articleId: Int = 0
}
