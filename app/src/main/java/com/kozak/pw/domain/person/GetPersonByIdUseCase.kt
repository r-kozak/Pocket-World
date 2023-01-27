package com.kozak.pw.domain.person

class GetPersonByIdUseCase(private val personItemRepository: PersonItemRepository) {
    suspend operator fun invoke(personId: Long): PersonItem =
        personItemRepository.getPersonById(personId)
}