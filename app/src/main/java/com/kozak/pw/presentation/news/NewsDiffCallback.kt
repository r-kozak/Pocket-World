package com.kozak.pw.presentation.news

import androidx.recyclerview.widget.DiffUtil
import com.kozak.pw.domain.news.News

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
}