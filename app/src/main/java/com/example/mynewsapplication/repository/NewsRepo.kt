package com.example.mynewsapplication.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mynewsapplication.apis.APIs
import com.example.mynewsapplication.database.NewsDatabase
import com.example.mynewsapplication.models.NewsHeadlines
import com.example.mynewsapplication.utils.Constants
import com.example.mynewsapplication.models.Articles
import com.google.gson.Gson
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepo @Inject constructor(private val retrofit: Retrofit, private val context: Context) :
    Repo() {

    private val _newsHeadlines: MutableLiveData<List<Articles>> = MutableLiveData()
    var newsHeadlines: LiveData<List<Articles>> = _newsHeadlines

    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    private val _error: MutableLiveData<String> = MutableLiveData()
    var error: LiveData<String> = _error

    fun getAllNews() {
        val apis: APIs = retrofit.create(APIs::class.java)
        job = CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            val response = apis.getNewsHeadlinesCoroutine("us", Constants.API_KEY)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _newsHeadlines.postValue(response.body()?.articles)
                    loading.postValue(false)
                } else {
                    _error.postValue("Error: ${response.message()}")
                    loading.postValue(false)
                }
            }
        }
    }

}
