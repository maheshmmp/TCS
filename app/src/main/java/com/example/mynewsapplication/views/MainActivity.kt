package com.example.mynewsapplication.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynewsapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setNewsFragment()
    }

    private fun setNewsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rl_news_container, NewsFragment())
            .commitAllowingStateLoss()
    }
}