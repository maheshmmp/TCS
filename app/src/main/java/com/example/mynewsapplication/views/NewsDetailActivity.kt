package com.example.mynewsapplication.views

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.mynewsapplication.R
import com.example.mynewsapplication.databinding.ActivityNewsDetailBinding
import com.example.mynewsapplication.utils.Constants
import com.example.mynewsapplication.models.Articles
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.content_news_detail.*
import java.text.SimpleDateFormat


class NewsDetailActivity : AppCompatActivity() {

   private lateinit var mBinding : ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewsDetailBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        setSupportActionBar(toolbar)
        toolbar.contentInsetStartWithNavigation = 0
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getNewsDataFromIntent()
    }


    private fun getNewsDataFromIntent() {
        val newsArticle = intent?.getSerializableExtra(Constants.NEWS_DATA)
        newsArticle?.also {
            setUpNewsLayout(it as Articles)
        }
    }

    private fun setUpNewsLayout(article: Articles) {
        mBinding.item = article
        article.urlToImage?.also { loadImage(it, mBinding.sdvNewsDetailsCover) }
        article.title?.also { toolbar.title = it }
    }

    private fun loadImage(id: String?, productImage: ImageView) {
        Glide.with(productImage.context).load(id)
            .placeholder(R.color.light_cyan)
            .error(R.color.light_coral).fitCenter()
            .into(productImage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
