package com.kozak.pw.domain.person

import kotlinx.datetime.LocalDateTime

data class PersonItem(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDateTime,
    val deathDate: LocalDateTime,
    val isAlive: Boolean,
    val sex: Sex
) {
    enum class Sex {
        MALE, FEMALE
    }
}

