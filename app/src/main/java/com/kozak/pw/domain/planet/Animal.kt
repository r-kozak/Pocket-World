package com.kozak.pw.domain.planet

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.PwAny
import kotlinx.datetime.LocalDateTime

abstract class Animal (
    var birthDate: LocalDateTime,
    val sex: Sex,
    var intelligence: Int = 0,
    var beauty: Int = 0,
    var luck: Int = 0,
    var strength: Int = PwConstants.DEFAULT_ANIMAL_STRENGTH,
    var isAlive: Boolean = true,
    var deathDate: LocalDateTime? = null
) : PwAny() {
    enum class Sex {
        MALE, FEMALE
    }
}