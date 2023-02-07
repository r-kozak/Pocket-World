package com.kozak.pw.domain.person

class EditPersonUseCase(private val personRepository: PersonRepository) {
    suspend operator fun invoke(person: Person) {
        personRepository.updatePerson(person)
    }
}