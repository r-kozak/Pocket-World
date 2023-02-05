package com.kozak.pw.data.news

import com.kozak.pw.domain.news.News

class NewsMapper {

    fun mapEntityToItem(newsEntity: NewsEntity): News {
        return with(newsEntity) {
            News(
                id = id,
                title = title,
                text = text,
                priority = priority,
                read = read
            )
        }
    }

    fun mapItemToEntity(news: News): NewsEntity {
        return with(news) {
            NewsEntity(
                id = id,
                title = title,
                text = text,
                priority = priority,
                read = read
            )
        }
    }

    fun mapEntitiesListToItemsList(entities: List<NewsEntity>) =
        entities.map { mapEntityToItem(it) }
}