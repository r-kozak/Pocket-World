package com.kozak.pw.domain.news

import com.kozak.pw.domain.repository.NewsItemRepository

class GetNewsListUseCase(private val newsItemRepository: NewsItemRepository) {
    operator fun invoke(): List<NewsItem> = newsItemRepository.getNewsList()
}