package com.kozak.pw.domain.news

class GetNewsListUseCase(private val newsItemRepository: NewsItemRepository) {
    operator fun invoke(): List<NewsItem> = newsItemRepository.getNewsList()
}