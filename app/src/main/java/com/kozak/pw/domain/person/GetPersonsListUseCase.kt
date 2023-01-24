package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData

class GetPersonsListUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(): LiveData<List<PersonItem>> = personItemRepository.getPersonItemsList()
}