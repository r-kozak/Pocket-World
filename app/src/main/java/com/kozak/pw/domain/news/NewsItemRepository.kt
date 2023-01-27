package com.kozak.pw.domain.news

interface NewsItemRepository {
    fun addNews(newsItem: NewsItem)
    fun getNewsList(): List<NewsItem>
    fun readNews(newsItemId: Long)
}