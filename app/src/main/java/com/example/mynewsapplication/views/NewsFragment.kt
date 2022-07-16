package com.example.mynewsapplication.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplication.R
import com.example.mynewsapplication.adapter.NewsHeadlinesAdapter
import com.example.mynewsapplication.models.Articles
import com.example.mynewsapplication.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    //Using HILT
    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelChanges()
        newsViewModel.getNews()
    }

    private fun observeViewModelChanges() {
        newsViewModel.newsHeadlines.observe(viewLifecycleOwner) {
            it?.also {
                setNews(it)
            }
        }

        newsViewModel.loading.observe(viewLifecycleOwner) {
            it?.also {
                progress.apply {
                    visibility = if (it) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }

        newsViewModel.error.observe(viewLifecycleOwner) {
            activity?.also { context -> Toast.makeText(context, it, Toast.LENGTH_LONG) }
        }
    }

    private fun setNews(data: List<Articles>) {
        activity?.also {
            rvNewsHeadlines.apply {
                visibility = View.VISIBLE
                adapter = NewsHeadlinesAdapter(it, data, true)
                layoutManager = LinearLayoutManager(it)
            }
        }
    }

}