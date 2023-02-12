package com.kozak.pw.domain.news

class ReadNewsUseCase(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(newsId: Long) {
        newsRepository.getItemSync(newsId)?.let {
            it.read = true
            newsRepository.update(it)
        }
    }
}