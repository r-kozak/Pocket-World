package com.kozak.pw.domain.person

class CreateNewPersonUseCase(private val repository: PersonItemRepository) {
    suspend operator fun invoke() {
        val personItem = PersonGenerator.generate()
        repository.addPerson(personItem)
    }
}



