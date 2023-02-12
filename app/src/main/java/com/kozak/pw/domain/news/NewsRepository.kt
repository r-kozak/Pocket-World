package com.kozak.pw.domain.news

import androidx.lifecycle.LiveData
import com.kozak.pw.domain.BaseRepository

interface NewsRepository : BaseRepository<News> {

    fun getNewsList(): LiveData<List<News>>

    suspend fun getNewsListSync(): List<News>
}