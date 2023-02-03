package com.kozak.pw.data.news

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.news.News
import com.kozak.pw.domain.news.NewsRepository

object NewsRepositoryImpl : NewsRepository {
    private val news = mutableListOf<News>()

    private var autoincrementId: Long = 1

    override fun addNews(news: News) {
        if (news.id == PwConstants.DEFAULT_ITEM_ID) {
            news.id = autoincrementId++
        }
        this.news.add(news)
    }

    override fun getNewsList(): List<News> {
        return news.toList()
    }

    override fun readNews(newsId: Long) {
        news.find { it.id == newsId }?.read = true
    }
}