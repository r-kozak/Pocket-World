package com.kozak.pw.domain.person

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class KillPersonUseCase(private val personRepository: PersonRepository) {

    suspend operator fun invoke(personId: Long) {
        personRepository.getItemSync(personId)?.let {
            it.deathDate = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            it.isAlive = false
            personRepository.update(it)
        }
    }
}