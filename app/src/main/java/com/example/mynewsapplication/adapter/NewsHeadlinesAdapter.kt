package com.example.mynewsapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapplication.R
import com.example.mynewsapplication.databinding.ItemMixNewsBinding
import com.example.mynewsapplication.databinding.ItemNewsHeadlineBinding
import com.example.mynewsapplication.models.Articles
import com.example.mynewsapplication.utils.Constants
import com.example.mynewsapplication.views.NewsDetailActivity
import java.text.SimpleDateFormat

class NewsHeadlinesAdapter(
    private val context: Context,
    private val newsHeadlines: List<Articles>,
    private val viewType: Boolean
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TOP_HEADLINES -> TypeTopHeadlinesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.item_news_headline, parent, false
                )
            )
            else -> MixNewsViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_mix_news,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        newsHeadlines.also {
            when (holder) {
                is TypeTopHeadlinesViewHolder -> holder.bindToView(it[position])
                is MixNewsViewHolder -> holder.bindToView(it[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return newsHeadlines.size ?: 0
    }

    inner class TypeTopHeadlinesViewHolder(private val mBinding: ItemNewsHeadlineBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bindToView(article: Articles) {
            mBinding.item = article
            article.urlToImage?.also { loadImage(it, mBinding.ivNewsCover) }
            mBinding.cvContainer.setOnClickListener {
                context.startActivity(
                    Intent(context, NewsDetailActivity::class.java).putExtra(
                        Constants.NEWS_DATA, article
                    )
                )
            }
        }
    }

    inner class MixNewsViewHolder(private val mBinding: ItemMixNewsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bindToView(article: Articles) {
            mBinding.item = article
            mBinding.cvMixNewsContainer.setOnClickListener {
                context.startActivity(
                    Intent(context, NewsDetailActivity::class.java).putExtra(
                        Constants.NEWS_DATA, article
                    )
                )
            }
        }
    }

    private fun loadImage(id: String?, productImage: ImageView) {
        Glide.with(productImage.context).load(id)
            .placeholder(R.color.light_cyan)
            .error(R.color.light_coral).centerCrop()
            .into(productImage)
    }

    private fun getDayFromDate(dateValue: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateValue)
        return SimpleDateFormat("dd-MM-yyyy").format(date)
    }

    companion object {
        const val TYPE_TOP_HEADLINES = 0
        const val TYPE_MIX_NEWS = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewType) {
            TYPE_TOP_HEADLINES
        } else {
            TYPE_MIX_NEWS
        }
    }

}