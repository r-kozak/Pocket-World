package com.kozak.pw.data.news

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.domain.news.News
import com.kozak.pw.domain.news.NewsRepository

class NewsRepositoryImpl(application: Application) : NewsRepository {

    private val newsDao = AppDatabase.getInstance(application).newsDao()
    private val mapper = NewsMapper()

    override fun getNewsList(): LiveData<List<News>> =
        Transformations.map(newsDao.getNewsList()) {
            mapper.mapEntitiesListToItemsList(it)
        }

    override suspend fun updateNews(news: News) = addOrUpdateNews(news)

    override suspend fun addNews(news: News) = addOrUpdateNews(news)

    private suspend fun addOrUpdateNews(news: News) {
        val newsEntity = mapper.mapItemToEntity(news)
        newsDao.addOrUpdateNews(newsEntity)
    }

    override suspend fun getNewsById(newsId: Long): News {
        val newsEntity = newsDao.getNewsById(newsId)
        return mapper.mapEntityToItem(newsEntity)
    }
}