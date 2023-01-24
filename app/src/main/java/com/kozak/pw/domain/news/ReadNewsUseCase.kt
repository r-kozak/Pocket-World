package com.kozak.pw.domain.news

import com.kozak.pw.domain.repository.NewsItemRepository

class ReadNewsUseCase(private val newsItemRepository: NewsItemRepository) {
    operator fun invoke(newsItemId: Long) = newsItemRepository.readNews(newsItemId)
}