package com.kozak.pw.domain.news

class ReadNewsUseCase(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(newsId: Long) {
        val news = newsRepository.getNewsById(newsId)
        news.read = true
        newsRepository.updateNews(news)
    }
}