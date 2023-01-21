package com.kozak.pw.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.person.PersonItem
import com.kozak.pw.domain.repository.PersonItemRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object PersonItemRepositoryImpl : PersonItemRepository {
    private val persons = mutableListOf<PersonItem>()
    private var autoincrementId: Long = 1

    private val personItemsList = MutableLiveData<List<PersonItem>>()

    init {
        repeat(25) {
            addPerson(PersonGenerator.generate())
        }
    }

    override fun addPerson(personItem: PersonItem) {
        if (personItem.id == PwConstants.INITIAL_ITEM_ID) {
            personItem.id = autoincrementId++
        }
        persons.add(personItem)
        updateList()
    }

    override fun editPerson(personItem: PersonItem) {
        getPersonById(personItem.id)
            .let { persons.indexOf(it) }
            .let {
                persons.removeAt(it)
                persons.add(it, personItem)
            }
        updateList()
    }

    override fun getPersonById(personId: Long): PersonItem {
        return persons
            .find { it.id == personId }
            ?: throw RuntimeException("Person with id $personId not found!")
    }

    override fun getPersonItemsList(): LiveData<List<PersonItem>> {
        return personItemsList
    }

    override fun killPerson(personId: Long) {
        val person = getPersonById(personId)
        person.deathDate = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        person.isAlive = false
        updateList()
    }

    override fun togglePersonFavorite(personId: Long) {
        val person = getPersonById(personId)
        person.isFavorite = !person.isFavorite
        updateList()
    }

    private fun updateList() {
        personItemsList.value = persons.filter { it.isAlive }.toList()
    }
}