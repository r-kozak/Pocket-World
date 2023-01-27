package com.kozak.pw.data.person

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.domain.person.PersonItem
import com.kozak.pw.domain.person.PersonItemRepository

class PersonItemRepositoryImpl(application: Application) : PersonItemRepository {

    private val personItemDao = AppDatabase.getInstance(application).personItemDao()
    private val mapper = PersonItemMapper()

    override suspend fun addPerson(personItem: PersonItem) = addOrUpdatePerson(personItem)

    override suspend fun updatePerson(personItem: PersonItem) = addOrUpdatePerson(personItem)

    private suspend fun addOrUpdatePerson(personItem: PersonItem) {
        val personEntity = mapper.mapItemToEntity(personItem)
        personItemDao.addOrUpdatePerson(personEntity)
    }

    override suspend fun getPersonById(personId: Long): PersonItem {
        val personEntity = personItemDao.getPersonById(personId)
        return mapper.mapEntityToItem(personEntity)
    }

    override fun getPersonItemsList(): LiveData<List<PersonItem>> =
        Transformations.map(personItemDao.getPersonsList()) {
            mapper.mapEntitiesListToItemsList(it)
        }
}