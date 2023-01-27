package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData

interface PersonItemRepository {
    fun addPerson(personItem: PersonItem)
    fun updatePerson(personItem: PersonItem)
    fun getPersonById(personId: Long): PersonItem
    fun getPersonItemsList(): LiveData<List<PersonItem>>
}