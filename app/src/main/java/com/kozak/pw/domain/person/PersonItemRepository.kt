package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData

interface PersonItemRepository {
    suspend fun addPerson(personItem: PersonItem)
    suspend fun updatePerson(personItem: PersonItem)
    suspend fun getPersonById(personId: Long): PersonItem
    fun getPersonItemsList(): LiveData<List<PersonItem>>
}