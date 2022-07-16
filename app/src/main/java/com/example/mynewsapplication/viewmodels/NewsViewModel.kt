package com.example.mynewsapplication.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.room.Ignore
import com.example.mynewsapplication.repository.NewsRepo

class NewsViewModel @ViewModelInject @Ignore constructor(
    private val repository: NewsRepo
) : ViewModel() {

    var newsHeadlines = repository.newsHeadlines
    var error = repository.error
    var loading = repository.loading

    fun getNews() {
        repository.getAllNews()
    }

}

