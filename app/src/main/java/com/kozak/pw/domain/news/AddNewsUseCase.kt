package com.kozak.pw.domain.news

class AddNewsUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(news: News) = newsRepository.addNews(news)
}