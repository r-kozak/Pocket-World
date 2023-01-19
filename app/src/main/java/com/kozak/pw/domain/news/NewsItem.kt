package com.kozak.pw.domain.news

/**
 * Model to store information about news.
 * User can mark or read news.
 * @author Roman Kozak, 2023/01/19
 */
data class NewsItem(
    val id: Long,
    val title: String,
    val text: String,
    val priority: NewsPriority,
    val marked: Boolean,
    val read: Boolean
) {
    enum class NewsPriority(order: Int) {
        HIGH(1), MIDDLE(2), LOW(3)
    }
}
