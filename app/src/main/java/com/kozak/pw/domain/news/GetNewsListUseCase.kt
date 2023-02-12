package com.kozak.pw.domain.news

import androidx.lifecycle.LiveData

class GetNewsListUseCase(private val newsRepository: NewsRepository) {

    operator fun invoke(): LiveData<List<News>> = newsRepository.getNewsList()
}