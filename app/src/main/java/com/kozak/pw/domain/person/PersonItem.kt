package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class PersonItem(
    var firstName: String,
    var lastName: String,
    val birthDate: LocalDateTime,
    val sex: Sex,
    var isFavorite: Boolean = false,
    var deathDate: LocalDateTime? = null,
    var isAlive: Boolean = true,
    var id: Long = PwConstants.INITIAL_ITEM_ID
) {
    enum class Sex {
        MALE, FEMALE
    }

    fun fullName() = "$firstName $lastName"

    fun shortBirthDate() = birthDate.toJavaLocalDateTime()
        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))!!
}

