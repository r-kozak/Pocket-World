package com.kozak.pw.domain.person

class EditPersonUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personItem: PersonItem) {
        personItemRepository.updatePerson(personItem)
    }
}