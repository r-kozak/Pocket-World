package com.kozak.pw.domain.person

class GetPersonByIdUseCase(private val personRepository: PersonRepository) {

    suspend operator fun invoke(personId: Long): Person? =
        personRepository.getItemSync(personId)
}