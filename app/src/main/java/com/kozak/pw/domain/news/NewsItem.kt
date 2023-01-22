package com.kozak.pw.domain.news

import com.kozak.pw.PwConstants

/**
 * Model to store information about news.
 * User can mark or read news.
 * @author Roman Kozak, 2023/01/19
 */
data class NewsItem(
    var title: String,
    var text: String,
    val priority: NewsPriority,
    var marked: Boolean,
    var read: Boolean,
    var id: Long = PwConstants.DEFAULT_ITEM_ID
) {
    enum class NewsPriority(order: Int) {
        HIGH(1), MIDDLE(2), LOW(3)
    }
}
