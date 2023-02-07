package com.kozak.pw.domain.person

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class KillPersonUseCase(private val personRepository: PersonRepository) {

    suspend operator fun invoke(personId: Long) {
        val person = personRepository.getPersonById(personId)
        person.deathDate = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        person.isAlive = false
        personRepository.updatePerson(person)
    }
}