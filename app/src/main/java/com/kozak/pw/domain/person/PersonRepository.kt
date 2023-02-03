package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData

interface PersonRepository {
    suspend fun addPerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun getPersonById(personId: Long): Person
    fun getPersonsList(): LiveData<List<Person>>
}