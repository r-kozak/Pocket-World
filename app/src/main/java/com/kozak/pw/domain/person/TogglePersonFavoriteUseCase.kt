package com.kozak.pw.domain.person

class TogglePersonFavoriteUseCase(private val personRepository: PersonRepository) {
    suspend operator fun invoke(personId: Long) {
        personRepository.getItemSync(personId)?.let {
            it.isFavorite = !it.isFavorite
            personRepository.update(it)
        }
    }
}
