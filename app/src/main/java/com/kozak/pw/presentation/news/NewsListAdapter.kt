package com.kozak.pw.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kozak.pw.databinding.NewsBinding
import com.kozak.pw.domain.news.News

class NewsListAdapter :
    ListAdapter<News, NewsListViewHolder>(NewsDiffCallback()) {

    var onNewsClickListener: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = NewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.root.setOnClickListener {
                    onNewsClickListener?.invoke(this)
                }
                bind(this)
            }
        }
    }
}