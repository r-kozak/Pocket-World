package com.kozak.pw.domain.news

class AddNewsUseCase(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(news: News) = newsRepository.insert(news)
}