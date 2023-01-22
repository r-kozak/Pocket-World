package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class PersonItem(
    val birthDate: LocalDateTime,
    val sex: Sex,
    var firstName: String = "",
    var lastName: String = "",
    var strength: Int = PwConstants.DEFAULT_PERSON_STRENGTH,
    var id: Long = PwConstants.INITIAL_ITEM_ID,
    var isFavorite: Boolean = false,
    var isAlive: Boolean = true,
    var deathDate: LocalDateTime? = null
) {
    enum class Sex {
        MALE, FEMALE
    }

    fun fullName() = "$firstName $lastName"

    fun shortBirthDate() = birthDate.toJavaLocalDateTime()
        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))!!
}

