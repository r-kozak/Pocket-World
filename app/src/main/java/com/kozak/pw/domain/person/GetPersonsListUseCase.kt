package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData

class GetPersonsListUseCase(private val personRepository: PersonRepository) {

    operator fun invoke(): LiveData<List<Person>> = personRepository.getAlivePersonsList()
}