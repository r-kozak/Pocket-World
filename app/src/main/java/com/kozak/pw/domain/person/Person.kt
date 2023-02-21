package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.Size
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
    mass: Long,
    size: Size
) : Animal(birthDate, sex, intelligence, beauty, luck, strength, isAlive, deathDate, mass, size) {

    companion object {
        val INITIAL_MASS_KG: LongRange = 3L..5L
        val INITIAL_SIZE_WIDTH_CM = 12..15
        val INITIAL_SIZE_HEIGHT_CM = 45..55
    }

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

    override fun calculateMass(): Long {
        return mass
    }

    override fun calculateSize(): Size {
        return size!!
    }

    override fun toString(): String {
        return "name: ${name}, birthDate: ${birthDate.short()}, sex: $sex, " +
                "intelligence: $intelligence, beauty: $beauty, luck: $luck, strength: $strength"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false

        if (createdAt != other.createdAt) return false
        if (health != other.health) return false
        if (mass != other.mass) return false
        if (birthDate != other.birthDate) return false
        if (sex != other.sex) return false
        if (intelligence != other.intelligence) return false
        if (beauty != other.beauty) return false
        if (luck != other.luck) return false
        if (strength != other.strength) return false
        if (isAlive != other.isAlive) return false
        if (deathDate != other.deathDate) return false
        if (name != other.name) return false
        if (isFavorite != other.isFavorite) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        return true
    }

    override fun hashCode(): Int {
        var result = isFavorite.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + health.hashCode()
        result = 31 * result + mass.hashCode()
        result = 31 * result + sex.hashCode()
        result = 31 * result + intelligence.hashCode()
        result = 31 * result + beauty.hashCode()
        result = 31 * result + luck.hashCode()
        result = 31 * result + strength.hashCode()
        result = 31 * result + isAlive.hashCode()
        result = 31 * result + deathDate.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + isFavorite.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }
}

