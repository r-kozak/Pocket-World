package com.kozak.pw.data.person

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.person.Person
import com.kozak.pw.domain.person.PersonRepository

class PersonRepositoryImpl(application: Application) :
    BaseRepositoryImpl<PersonEntity, Person>(), PersonRepository {

    override val dao = AppDatabase.getInstance(application).personDao()
    override val mapper = PersonMapper()

    override fun getAlivePersonsList(): LiveData<List<Person>> {
        val personsList = dao.getAlivePersonsList()
        return Transformations.map(personsList) {
            mapper.mapEntitiesListToItemsList(it)
        }
    }
}