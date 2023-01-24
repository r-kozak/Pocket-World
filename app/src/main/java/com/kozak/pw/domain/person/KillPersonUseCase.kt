package com.kozak.pw.domain.person

class KillPersonUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personId: Long) = personItemRepository.killPerson(personId)
}