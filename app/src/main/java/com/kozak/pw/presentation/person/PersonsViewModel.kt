package com.kozak.pw.presentation.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kozak.pw.data.person.PersonItemRepositoryImpl
import com.kozak.pw.domain.person.CreateNewPersonUseCase
import com.kozak.pw.domain.person.GetPersonsListUseCase
import com.kozak.pw.domain.person.KillPersonUseCase
import com.kozak.pw.domain.person.TogglePersonFavoriteUseCase

class PersonsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PersonItemRepositoryImpl(application) // TODO get rid of dependency to data layer

    private val getPersonsUseCase = GetPersonsListUseCase(repository)
    private val killPersonUseCase = KillPersonUseCase(repository)
    private val togglePersonFavoriteUseCase = TogglePersonFavoriteUseCase(repository)
    private val createNewPersonUseCase = CreateNewPersonUseCase(repository)

    val personItemsList = getPersonsUseCase()

    fun killPerson(personId: Long) {
        killPersonUseCase(personId)
    }

    fun togglePersonFavorite(personId: Long) {
        togglePersonFavoriteUseCase(personId)
    }

    fun createNewPerson() {
        createNewPersonUseCase()
    }
}