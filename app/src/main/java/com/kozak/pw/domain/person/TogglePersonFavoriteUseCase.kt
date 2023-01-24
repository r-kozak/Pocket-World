package com.kozak.pw.domain.person

class TogglePersonFavoriteUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personId: Long) = personItemRepository.togglePersonFavorite(personId)
}
