package com.example.mynewsapplication.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Articles(

    @ColumnInfo
    @SerializedName("author")
    var author: String? = null,

    @ColumnInfo
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo
    @SerializedName("description")
    var description: String? = null,

    @ColumnInfo
    @SerializedName("url")
    var url: String? = null,

    @ColumnInfo
    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @ColumnInfo
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @ColumnInfo
    @SerializedName("content")
    var content: String? = null,

    @ColumnInfo
    @SerializedName("saved")
    var isSaved: Boolean? = null

):Serializable {
    @PrimaryKey(autoGenerate = true)
    var articleId: Int? = 0
}
