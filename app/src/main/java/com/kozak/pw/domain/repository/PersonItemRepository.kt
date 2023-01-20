package com.kozak.pw.domain.repository

import com.kozak.pw.domain.person.PersonItem

interface PersonItemRepository {
    fun addPerson(personItem: PersonItem)
    fun editPerson(personItem: PersonItem)
    fun getPersonById(personId: Long): PersonItem
    fun getPersonItemsList(): List<PersonItem>
    fun killPerson(personId: Long)
}