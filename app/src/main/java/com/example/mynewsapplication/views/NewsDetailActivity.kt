package com.example.mynewsapplication.views

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mynewsapplication.R
import com.example.mynewsapplication.utils.Constants
import com.example.mynewsapplication.models.Articles
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.content_news_detail.*
import java.text.SimpleDateFormat


class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
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
        article.urlToImage?.also { loadImage(it, sdvNewsDetailsCover) }
        article.title?.also { toolbar.title = it }
        article.title?.also { tvNewsDetailsTitle.text = it }
        article.description?.also { tvNewsDetailsDescription.text = it }
        article.publishedAt?.also { tvNewsDetailsPublishedDate.text = getDayFromDate(it) }
        article.author?.also { tvNewsDetailsAuthor.text =  it }
        article.content?.also {
            tvNewsDetailsOtherData.text = it
        }
    }

    private fun loadImage(id: String?, productImage: ImageView) {
        Glide.with(productImage.context).load(id)
            .placeholder(R.color.light_cyan)
            .error(R.color.light_coral).fitCenter()
            .into(productImage)
    }

    private fun getDayFromDate(dateValue: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateValue)
        return SimpleDateFormat("dd-MM-yyyy").format(date)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
