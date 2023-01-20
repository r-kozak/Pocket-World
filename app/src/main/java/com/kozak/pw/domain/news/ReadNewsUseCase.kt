package com.kozak.pw.domain.news

import com.kozak.pw.domain.repository.NewsItemRepository

class ReadNewsUseCase(private val newsItemRepository: NewsItemRepository) {
    fun readNews(newsItemId: Long) = newsItemRepository.readNews(newsItemId)
}