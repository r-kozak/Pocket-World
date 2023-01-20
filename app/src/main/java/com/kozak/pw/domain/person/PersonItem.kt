package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import kotlinx.datetime.LocalDateTime

data class PersonItem(
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDateTime,
    val deathDate: LocalDateTime,
    var isAlive: Boolean,
    val sex: Sex,
    var id: Long = PwConstants.INITIAL_ITEM_ID
) {
    enum class Sex {
        MALE, FEMALE
    }
}

