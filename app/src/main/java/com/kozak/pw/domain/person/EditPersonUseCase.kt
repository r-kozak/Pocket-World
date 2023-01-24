package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class EditPersonUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personItem: PersonItem) {
        personItemRepository.editPerson(personItem)
    }
}