package com.kozak.pw.presentation.person

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kozak.pw.data.person.PersonItemRepositoryImpl
import com.kozak.pw.domain.person.GetPersonsListUseCase
import com.kozak.pw.domain.person.KillPersonUseCase
import com.kozak.pw.domain.person.TogglePersonFavoriteUseCase

class PersonsViewModel : ViewModel() {
    private val repository = PersonItemRepositoryImpl(Application()) // TODO get rid of dependency to data layer

    private val getPersonsUseCase = GetPersonsListUseCase(repository)
    private val killPersonUseCase = KillPersonUseCase(repository)
    private val togglePersonFavoriteUseCase = TogglePersonFavoriteUseCase(repository)

    val personItemsList = getPersonsUseCase()

    fun killPerson(personId: Long) {
        killPersonUseCase(personId)
    }

    fun togglePersonFavorite(personId: Long) {
        togglePersonFavoriteUseCase(personId)
    }
}