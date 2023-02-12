package com.kozak.pw.data.news

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.news.News
import com.kozak.pw.domain.news.NewsRepository

class NewsRepositoryImpl(application: Application) :
    BaseRepositoryImpl<NewsEntity, News>(), NewsRepository {

    override val dao = AppDatabase.getInstance(application).newsDao()
    override val mapper = NewsMapper()

    override fun getNewsList(): LiveData<List<News>> {
        return Transformations.map(dao.getNewsList()) {
            mapper.mapEntitiesListToItemsList(it)
        }
    }

    override fun getNewsListSync(): List<News> {
        return mapper.mapEntitiesListToItemsList(dao.getNewsListSync())
    }
}