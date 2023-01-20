package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class KillPersonUseCase(private val personItemRepository: PersonItemRepository) {
    fun killPerson(personId: Long) = personItemRepository.killPerson(personId)
}