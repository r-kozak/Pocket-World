package com.kozak.pw.data

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.person.PersonItem
import com.kozak.pw.domain.repository.PersonItemRepository

object PersonItemRepositoryImpl: PersonItemRepository {
    private val persons = mutableListOf<PersonItem>()

    private var autoincrementId: Long = 1

    override fun addPerson(personItem: PersonItem) {
        if (personItem.id == PwConstants.INITIAL_ITEM_ID) {
            personItem.id = autoincrementId++
        }
        persons.add(personItem)
    }

    override fun editPerson(personItem: PersonItem) {
        getPersonById(personItem.id)
            .let { persons.indexOf(it) }
            .let {
                persons.removeAt(it)
                persons.add(it, personItem)
            }
    }

    override fun getPersonById(personId: Long): PersonItem {
        return persons
            .find { it.id == personId }
            ?: throw RuntimeException("Person with id $personId not found!")
    }

    override fun getPersonItemsList(): List<PersonItem> {
        return persons.toList()
    }

    override fun killPerson(personId: Long) {
        getPersonById(personId).isAlive = false
    }
}