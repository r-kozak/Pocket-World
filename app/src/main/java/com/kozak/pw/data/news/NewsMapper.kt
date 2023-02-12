package com.kozak.pw.data.news

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.news.News

class NewsMapper : PwMapper<NewsEntity, News> {

    override fun mapEntityToItem(entity: NewsEntity): News {
        return with(entity) {
            News(
                id = id,
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
                title = title,
                text = text,
                priority = priority,
                read = read
            )
        }
    }
}