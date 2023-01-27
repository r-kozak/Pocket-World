package com.kozak.pw.domain.news

class ReadNewsUseCase(private val newsItemRepository: NewsItemRepository) {
    operator fun invoke(newsItemId: Long) = newsItemRepository.readNews(newsItemId)
}