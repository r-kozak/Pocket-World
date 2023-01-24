package com.kozak.pw.domain.news

import com.kozak.pw.domain.repository.NewsItemRepository

class AddNewsUseCase(private val newsItemRepository: NewsItemRepository) {
    operator fun invoke(newsItem: NewsItem) = newsItemRepository.addNews(newsItem)
}