package com.kozak.pw.data.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.data.BaseEntity
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.domain.news.News

/**
 * Entity to store information about news.
 * User can read news.
 * @author Roman Kozak, 2023/01/19
 */
@Entity(tableName = TablesNamesConstants.NEWS_ENTITY_TABLE_NAME)
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val createdAt: String,
    val title: String,
    val text: String,
    val priority: News.NewsPriority,
    val read: Boolean
) : BaseEntity()