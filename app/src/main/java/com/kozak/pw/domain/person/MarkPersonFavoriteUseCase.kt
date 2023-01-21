package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class MarkPersonFavoriteUseCase(private val personItemRepository: PersonItemRepository) {
    fun togglePersonFavorite(personId: Long) = personItemRepository.togglePersonFavorite(personId)
}
