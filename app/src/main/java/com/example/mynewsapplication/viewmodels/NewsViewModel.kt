package com.example.mynewsapplication.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.room.Ignore
import com.example.mynewsapplication.repository.NewsRepo

class NewsViewModel @ViewModelInject @Ignore constructor(
    private val repo: NewsRepo
) : ViewModel() {

    var newsHeadlines = repo.newsHeadlines
    var error = repo.error
    var loading = repo.loading

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

    fun getNews() {
        repo.getAllNews()
    }

}

