package com.kozak.pw.presentation.news

import androidx.recyclerview.widget.RecyclerView
import com.kozak.pw.databinding.NewsBinding
import com.kozak.pw.domain.news.News

class NewsListViewHolder(val binding: NewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.tvNewsTitle.text = news.title
        binding.tvNewsText.text = news.text
    }
}