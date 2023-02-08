package com.kozak.pw.data.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM news WHERE read=:read")
    fun getNewsLiveDataList(read: Boolean = false): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE read=:read")
    fun getNewsList(read: Boolean = false): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdateNews(newsEntity: NewsEntity)
    @Query("SELECT * FROM news WHERE id=:newsId LIMIT 1")
    suspend fun getNewsById(newsId: Long): NewsEntity
}