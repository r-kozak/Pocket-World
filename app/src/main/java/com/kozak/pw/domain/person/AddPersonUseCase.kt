package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class AddPersonUseCase(private val personItemRepository: PersonItemRepository) {
    fun addPerson(personItem: PersonItem) = personItemRepository.addPerson(personItem)
}