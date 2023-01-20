package com.kozak.pw.domain.person

import com.kozak.pw.domain.repository.PersonItemRepository

class GetPersonsListUseCase(private val personItemRepository: PersonItemRepository) {
    fun getPersonItemsList(): List<PersonItem> = personItemRepository.getPersonItemsList()
}