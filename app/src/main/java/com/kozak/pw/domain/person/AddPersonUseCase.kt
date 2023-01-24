package com.kozak.pw.domain.person

class AddPersonUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personItem: PersonItem) = personItemRepository.addPerson(personItem)
}