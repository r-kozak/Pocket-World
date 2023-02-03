package com.kozak.pw.domain.news

class ReadNewsUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(newsId: Long) = newsRepository.readNews(newsId)
}