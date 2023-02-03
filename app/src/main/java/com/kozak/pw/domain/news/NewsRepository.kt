package com.kozak.pw.domain.news

interface NewsRepository {
    fun addNews(news: News)
    fun getNewsList(): List<News>
    fun readNews(newsId: Long)
}