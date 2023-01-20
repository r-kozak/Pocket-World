package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class GetPersonByIdUseCase(private val personItemRepository: PersonItemRepository) {
    fun getPersonById(personId: Long): PersonItem = personItemRepository.getPersonById(personId)
}