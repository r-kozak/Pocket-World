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
    strength: Int = PwConstants.DEFAULT_ANIMAL_STRENGTH,
    isAlive: Boolean = true,
    deathDate: LocalDateTime? = null,
    var isFavorite: Boolean = false,
) : Animal(birthDate, sex, intelligence, beauty, luck, strength, isAlive, deathDate) {

    var firstName: String = PwConstants.DEFAULT_ITEM_NAME
        get() = name.split(" ")[0]
        set(value) {
            field = value
            name = "$value $lastName"
        }

    var lastName: String = PwConstants.DEFAULT_ITEM_NAME
        get() = name.split(" ")[1]
        set(value) {
            field = value
            name = "$firstName $value"
        }

    // TODO implement
    override fun calculateMass(): Long {
        return 0
    }

    override fun toString(): String {
        return "name: ${name}, birthDate: ${birthDate.short()}, sex: $sex, " +
                "intelligence: $intelligence, beauty: $beauty, luck: $luck, strength: $strength"
    }

    // TODO override equals and hashCode
}

