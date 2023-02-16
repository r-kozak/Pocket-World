package com.kozak.pw.data.news

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.news.News
import kotlinx.datetime.LocalDateTime

class NewsMapper : PwMapper<NewsEntity, News> {

    override fun mapEntityToItem(entity: NewsEntity): News {
        return with(entity) {
            News(
                id = id,
                createdAt = LocalDateTime.parse(createdAt),
                title = title,
                text = text,
                priority = priority,
                read = read
            )
        }
    }

    override fun mapItemToEntity(item: News): NewsEntity {
        return with(item) {
            NewsEntity(
                id = id,
                createdAt = createdAt.toString(),
                title = title,
                text = text,
                priority = priority,
                read = read
            )
        }
    }
}