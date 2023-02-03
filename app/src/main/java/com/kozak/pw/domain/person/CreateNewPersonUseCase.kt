package com.kozak.pw.domain.person

class CreateNewPersonUseCase(private val repository: PersonRepository) {
    suspend operator fun invoke() {
        val person = PersonGenerator.generate()
        repository.addPerson(person)
    }
}



