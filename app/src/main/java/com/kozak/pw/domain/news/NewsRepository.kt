package com.kozak.pw.domain.news

import androidx.lifecycle.LiveData

interface NewsRepository {
    suspend fun addNews(news: News)
    suspend fun updateNews(news: News)
    suspend fun getNewsById(newsId: Long): News
    fun getNewsLiveDataList(): LiveData<List<News>>
    fun getNewsList(): List<News>
}