package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.planet.Animal
import com.kozak.pw.short
import kotlinx.datetime.LocalDateTime

class Person(
    birthDate: LocalDateTime,
    sex: Sex,
    intelligence: Int = 0,
    beauty: Int = 0,
    luck: Int = 0,
    id: Long = PwConstants.DEFAULT_ITEM_ID,
    strength: Int = PwConstants.DEFAULT_ANIMAL_STRENGTH,
    isAlive: Boolean = true,
    deathDate: LocalDateTime? = null,
    var firstName: String = "",
    var lastName: String = "",
    var isFavorite: Boolean = false,
) : Animal(id, birthDate, sex, intelligence, beauty, luck, strength, isAlive, deathDate) {

    fun fullName() = "$firstName $lastName"

    override fun toString(): String {
        return "name: ${fullName()}, birthDate: ${birthDate.short()}, sex: $sex, " +
                "intelligence: $intelligence, beauty: $beauty, luck: $luck, strength: $strength"
    }
}

