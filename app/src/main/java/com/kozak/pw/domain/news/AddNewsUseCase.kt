package com.kozak.pw.domain.news

class AddNewsUseCase(private val newsItemRepository: NewsItemRepository) {
    operator fun invoke(newsItem: NewsItem) = newsItemRepository.addNews(newsItem)
}