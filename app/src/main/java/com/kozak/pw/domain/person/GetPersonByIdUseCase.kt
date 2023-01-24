package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class GetPersonByIdUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personId: Long): PersonItem = personItemRepository.getPersonById(personId)
}