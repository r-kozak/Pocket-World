package com.kozak.pw.domain.news

class GetNewsListUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(): List<News> = newsRepository.getNewsList()
}