package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData
import com.kozak.pw.domain.repository.PersonItemRepository

class GetPersonsListUseCase(private val personItemRepository: PersonItemRepository) {
    operator fun invoke(): LiveData<List<PersonItem>> = personItemRepository.getPersonItemsList()
}