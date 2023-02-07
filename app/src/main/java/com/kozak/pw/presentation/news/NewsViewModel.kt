package com.kozak.pw.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kozak.pw.domain.news.GetNewsListUseCase
import com.kozak.pw.domain.news.NewsRepository
import com.kozak.pw.domain.news.ReadNewsUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class NewsViewModel : ViewModel() {

    private val repository: NewsRepository by inject(NewsRepository::class.java)

    private val getNewsUseCase = GetNewsListUseCase(repository)
    private val readNewsUseCase = ReadNewsUseCase(repository)

    val newsList = getNewsUseCase()

    fun readNews(newsId: Long) {
        viewModelScope.launch {
            readNewsUseCase(newsId)
        }
    }
}