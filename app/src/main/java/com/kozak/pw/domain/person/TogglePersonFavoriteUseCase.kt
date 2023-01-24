package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class TogglePersonFavoriteUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(personId: Long) = personItemRepository.togglePersonFavorite(personId)
}
