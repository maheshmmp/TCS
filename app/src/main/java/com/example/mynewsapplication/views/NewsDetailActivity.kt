package com.example.mynewsapplication.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mynewsapplication.R
import com.example.mynewsapplication.databinding.ActivityNewsDetailBinding
import com.example.mynewsapplication.models.Articles
import com.example.mynewsapplication.utils.Constants
import kotlinx.android.synthetic.main.activity_news_detail.*


class NewsDetailActivity : AppCompatActivity() {

   private lateinit var mBinding : ActivityNewsDetailBinding
   private lateinit var article : Articles

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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getNewsDataFromIntent() {
        val newsArticle = intent?.getSerializableExtra(Constants.NEWS_DATA)
        newsArticle?.also {
            setUpNewsLayout(it as Articles)
        }
    }

    private fun setUpNewsLayout(article: Articles) {
        mBinding.item = article
        this.article = article
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
        return when (item.itemId) {
            R.id.shareButton -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = article.title
                val shareSubject = article.description
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                startActivity(Intent.createChooser(sharingIntent, "News"))
                return true
            }
            android.R.id.home ->{
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
