package com.kozak.pw.domain.repository

import com.kozak.pw.domain.news.NewsItem

interface NewsItemRepository {
    fun addNews(newsItem: NewsItem)
    fun getNewsList(): List<NewsItem>
    fun readNews(newsItemId: Long)
}