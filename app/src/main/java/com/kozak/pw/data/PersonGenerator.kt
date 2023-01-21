package com.kozak.pw.data

import com.kozak.pw.domain.person.PersonItem
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

private fun Int.roll() = (0 until this)
    .sumOf { (1..6).toList().random() }

private val maleFirstNames = listOf("Mukola", "Petro", "Stepan", "Oleh", "Matviy")
private val femaleFirstNames = listOf("Olha", "Tetyana", "Katya", "Nataha", "Alina")
private val lastNames = listOf("Veresen", "Begins", "Petrenko", "Simpson", "Kremez")

object PersonGenerator {
    fun generate(): PersonItem {
        val sex = PersonItem.Sex.values().random()
        val firstName = when (sex) {
            PersonItem.Sex.MALE -> maleFirstNames.random()
            PersonItem.Sex.FEMALE -> femaleFirstNames.random()
        }
        val lastName = lastNames.random()

        val birthDate = Clock.System.now()
            .minus(Duration.parse("P${5.roll() * 365}D"))
            .minus(Duration.parse("P${50.roll()}D"))
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return PersonItem(firstName, lastName, birthDate, sex)
    }
}