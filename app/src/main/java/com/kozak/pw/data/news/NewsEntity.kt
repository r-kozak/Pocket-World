package com.kozak.pw.data.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.domain.news.News

/**
 * Entity to store information about news.
 * User can read news.
 * @author Roman Kozak, 2023/01/19
 */
@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var title: String,
    var text: String,
    val priority: News.NewsPriority,
    var read: Boolean
)