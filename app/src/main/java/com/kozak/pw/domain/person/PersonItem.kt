package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import kotlinx.datetime.LocalDateTime

data class PersonItem(
    var firstName: String,
    var lastName: String,
    val birthDate: LocalDateTime,
    val sex: Sex,
    var deathDate: LocalDateTime? = null,
    var isAlive: Boolean = true,
    var id: Long = PwConstants.INITIAL_ITEM_ID
) {
    enum class Sex {
        MALE, FEMALE
    }
}

