package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class EditPersonUseCase(private val personItemRepository: PersonItemRepository) {
    fun editPerson(personItem: PersonItem) {
        personItemRepository.editPerson(personItem)
    }
}