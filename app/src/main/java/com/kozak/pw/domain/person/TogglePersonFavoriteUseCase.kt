package com.kozak.pw.domain.person

class TogglePersonFavoriteUseCase(private val personRepository: PersonRepository) {
    suspend operator fun invoke(personId: Long) {
        val person = personRepository.getPersonById(personId)
        person.isFavorite = !person.isFavorite
        personRepository.updatePerson(person)
    }
}
