package com.kozak.pw.domain.person

class TogglePersonFavoriteUseCase(private val personItemRepository: PersonItemRepository) {
    suspend operator fun invoke(personId: Long) {
        val personItem = personItemRepository.getPersonById(personId)
        personItem.isFavorite = !personItem.isFavorite
        personItemRepository.updatePerson(personItem)
    }
}
