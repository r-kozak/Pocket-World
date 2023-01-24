package com.kozak.pw.domain.person

class GetPersonByIdUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personId: Long): PersonItem = personItemRepository.getPersonById(personId)
}