package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.planet.Animal
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.random.nextLong
import kotlin.time.Duration

object PersonGenerator {

    fun generate(): Person {
        val sex = Animal.Sex.values().random()
        val randomYearsOld = Random.nextInt(PwConstants.NEW_PERSON_MAX_YEARS_OLD) * PwConstants.DAYS_IN_YEAR
        val randomDays = Random.nextInt(PwConstants.DAYS_IN_YEAR)
        val birthDate = Clock.System.now()
            .minus(Duration.parse("P${randomYearsOld}D"))
            .minus(Duration.parse("P${randomDays}D"))
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val initialMass = Random.nextLong(Person.INITIAL_MASS_KG)
        val initialWidth = Random.nextInt(Person.INITIAL_SIZE_WIDTH_CM)
        val initialHeight = Random.nextInt(Person.INITIAL_SIZE_HEIGHT_CM)
        val size = Size(initialWidth, initialHeight)

        return Person(birthDate, sex, mass = initialMass, size = size).apply {
            this.strength = Random.nextInt(PwConstants.PERSON_MAX_STRENGTH + 1)
        }
    }
}