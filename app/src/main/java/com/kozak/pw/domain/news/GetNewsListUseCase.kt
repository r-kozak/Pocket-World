package com.kozak.pw.domain.news

import com.kozak.pw.domain.news.NewsItem
import com.kozak.pw.domain.repository.NewsItemRepository

class GetNewsListUseCase(private val newsItemRepository: NewsItemRepository) {
    fun getNewsList(): List<NewsItem> = newsItemRepository.getNewsList()
}