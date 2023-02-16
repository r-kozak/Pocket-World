package com.kozak.pw.domain.news

import com.kozak.pw.PwConstants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Model to store information about news.
 * User can mark or read news.
 * @author Roman Kozak, 2023/01/19
 */
data class News(
    var title: String,
    var createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    var text: String,
    val priority: NewsPriority,
    var id: Long = PwConstants.DEFAULT_ITEM_ID,
    var read: Boolean = false
) {
    enum class NewsPriority(order: Int) {
        HIGH(1), MIDDLE(2), LOW(3)
    }
}
