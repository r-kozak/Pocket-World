package com.kozak.pw.domain.person

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class KillPersonUseCase(private val personItemRepository: PersonItemRepository) {

    operator fun invoke(personId: Long) {
        val personItem = personItemRepository.getPersonById(personId)
        personItem.deathDate = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        personItem.isAlive = false
        personItemRepository.updatePerson(personItem)
    }
}