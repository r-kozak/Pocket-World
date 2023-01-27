package com.kozak.pw.domain.person

import com.kozak.pw.PwConstants
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.time.Duration

object PersonGenerator {

    private val maleFirstNames = listOf("Mukola", "Petro", "Stepan", "Oleh", "Matviy", "Oleksiy")
    private val femaleFirstNames = listOf("Olha", "Tetyana", "Katya", "Nataha", "Alina", "Anna")
    private val lastNames = listOf("Veresen", "Begins", "Petrenko", "Simpson", "Kremez", "Muzika")

    fun generate(): PersonItem {
        val sex = PersonItem.Sex.values().random()
        val firstName = when (sex) {
            PersonItem.Sex.MALE -> maleFirstNames.random()
            PersonItem.Sex.FEMALE -> femaleFirstNames.random()
        }
        val lastName = lastNames.random()

        val randomYearsOld = Random.nextInt(PwConstants.NEW_PERSON_MAX_YEARS_OLD) * PwConstants.DAYS_IN_YEAR
        val randomDays = Random.nextInt(PwConstants.DAYS_IN_YEAR)
        val birthDate = Clock.System.now()
            .minus(Duration.parse("P${randomYearsOld}D"))
            .minus(Duration.parse("P${randomDays}D"))
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return PersonItem(birthDate, sex).apply {
            this.firstName = firstName
            this.lastName = lastName
            this.strength = Random.nextInt(PwConstants.PERSON_MAX_STRENGTH + 1)
        }
    }
}