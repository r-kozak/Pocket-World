package com.kozak.pw.data.person

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.domain.person.Person
import com.kozak.pw.domain.person.PersonRepository

class PersonRepositoryImpl(application: Application) : PersonRepository {

    private val personDao = AppDatabase.getInstance(application).personDao()
    private val mapper = PersonMapper()

    override suspend fun addPerson(person: Person) {
        val personEntity = mapper.mapItemToEntity(person)
        personDao.insert(personEntity)
    }

    override suspend fun updatePerson(person: Person) {
        val personEntity = mapper.mapItemToEntity(person)
        personDao.update(personEntity)
    }

    override suspend fun getPersonById(personId: Long): Person {
        val personEntity = personDao.getPersonById(personId)
        return mapper.mapEntityToItem(personEntity)
    }

    override fun getPersonsList(): LiveData<List<Person>> =
        Transformations.map(personDao.getAlivePersonsList()) {
            mapper.mapEntitiesListToItemsList(it)
        }
}