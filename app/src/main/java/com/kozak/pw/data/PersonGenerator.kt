package com.kozak.pw.data

import com.kozak.pw.domain.person.PersonItem
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.time.Duration

private val maleFirstNames = listOf("Mukola", "Petro", "Stepan", "Oleh", "Matviy", "Oleksiy")
private val femaleFirstNames = listOf("Olha", "Tetyana", "Katya", "Nataha", "Alina", "Anna")
private val lastNames = listOf("Veresen", "Begins", "Petrenko", "Simpson", "Kremez", "Muzika")

object PersonGenerator {
    fun generate(): PersonItem {
        val sex = PersonItem.Sex.values().random()
        val firstName = when (sex) {
            PersonItem.Sex.MALE -> maleFirstNames.random()
            PersonItem.Sex.FEMALE -> femaleFirstNames.random()
        }
        val lastName = lastNames.random()

        val birthDate = Clock.System.now()
            .minus(Duration.parse("P${Random.nextInt(20) * 365}D"))
            .minus(Duration.parse("P${Random.nextInt(365)}D"))
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return PersonItem(birthDate, sex).apply {
            this.firstName = firstName
            this.lastName = lastName
            this.strength = Random.nextInt(101)
        }
    }
}