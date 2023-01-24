package com.kozak.pw.presentation.view_model

import androidx.lifecycle.ViewModel
import com.kozak.pw.data.PersonItemRepositoryImpl
import com.kozak.pw.domain.person.*

class PersonsViewModel : ViewModel() {
    private val repository = PersonItemRepositoryImpl // TODO get rid of dependency to data layer

    private val getPersonsUseCase = GetPersonsListUseCase(repository)
    private val killPersonUseCase = KillPersonUseCase(repository)
    private val editPersonUseCase = EditPersonUseCase(repository)
    private val togglePersonFavoriteUseCase = TogglePersonFavoriteUseCase(repository)

    val personItemsList = getPersonsUseCase()

    fun killPerson(personId: Long) {
        killPersonUseCase(personId)
    }

    fun editPerson(personItem: PersonItem) {
        editPersonUseCase(personItem)
    }

    fun togglePersonFavorite(personId: Long) {
        togglePersonFavoriteUseCase(personId)
    }
}