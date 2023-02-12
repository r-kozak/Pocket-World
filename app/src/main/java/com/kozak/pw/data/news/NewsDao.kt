package com.kozak.pw.data.news

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants

@Dao
abstract class NewsDao(roomDatabase: RoomDatabase) :
    BaseDao<NewsEntity>(TablesNamesConstants.NEWS_ENTITY_TABLE_NAME, roomDatabase) {

    @Query("SELECT * FROM news WHERE read=:read")
    abstract fun getNewsList(read: Boolean = false): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE read=:read")
    abstract fun getNewsListSync(read: Boolean = false): List<NewsEntity>
}