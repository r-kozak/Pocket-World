package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.planet.Animal
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
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

        return Person(birthDate, sex).apply {
            this.strength = Random.nextInt(PwConstants.PERSON_MAX_STRENGTH + 1)
        }
    }
}