package com.kozak.pw.data.news

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.news.News
import com.kozak.pw.domain.news.NewsRepository

class NewsRepositoryImpl(application: Application) :
    BaseRepositoryImpl<NewsEntity, News>(), NewsRepository {

    override val dao = AppDatabase.getInstance(application).newsDao()
    override val mapper = NewsMapper()
}