package com.kozak.pw.data

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.news.NewsItem
import com.kozak.pw.domain.repository.NewsItemRepository

object NewsItemRepositoryImpl : NewsItemRepository {
    private val news = mutableListOf<NewsItem>()

    private var autoincrementId: Long = 1

    override fun addNews(newsItem: NewsItem) {
        if (newsItem.id == PwConstants.DEFAULT_ITEM_ID) {
            newsItem.id = autoincrementId++
        }
        news.add(newsItem)
    }

    override fun getNewsList(): List<NewsItem> {
        return news.toList()
    }

    override fun readNews(newsItemId: Long) {
        news.find { it.id == newsItemId }?.read = true
    }
}