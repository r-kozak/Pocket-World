package com.kozak.pw.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kozak.pw.data.PersonItemRepositoryImpl
import com.kozak.pw.domain.person.EditPersonUseCase
import com.kozak.pw.domain.person.GetPersonsListUseCase
import com.kozak.pw.domain.person.KillPersonUseCase
import com.kozak.pw.domain.person.PersonItem

class PersonsViewModel: ViewModel() {
    private val repository = PersonItemRepositoryImpl // TODO get rid of dependency to data layer

    private val getPersonsUseCase = GetPersonsListUseCase(repository)
    private val killPersonUseCase = KillPersonUseCase(repository)
    private val editPersonUseCase = EditPersonUseCase(repository)

    val personItemsList = getPersonsUseCase.getPersonItemsList()

    fun killPerson(personId: Long) {
        killPersonUseCase.killPerson(personId)
    }

    fun editPerson(personItem: PersonItem) {
        editPersonUseCase.editPerson(personItem)
    }
}