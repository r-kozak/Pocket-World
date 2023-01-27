package com.kozak.pw.presentation.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kozak.pw.data.person.PersonItemRepositoryImpl
import com.kozak.pw.domain.person.CreateNewPersonUseCase
import com.kozak.pw.domain.person.GetPersonsListUseCase
import com.kozak.pw.domain.person.KillPersonUseCase
import com.kozak.pw.domain.person.TogglePersonFavoriteUseCase
import kotlinx.coroutines.launch

class PersonsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PersonItemRepositoryImpl(application) // TODO get rid of dependency to data layer

    private val getPersonsUseCase = GetPersonsListUseCase(repository)
    private val killPersonUseCase = KillPersonUseCase(repository)
    private val togglePersonFavoriteUseCase = TogglePersonFavoriteUseCase(repository)
    private val createNewPersonUseCase = CreateNewPersonUseCase(repository)

    val personItemsList = getPersonsUseCase()

    fun killPerson(personId: Long) {
        viewModelScope.launch {
            killPersonUseCase(personId)
        }
    }

    fun togglePersonFavorite(personId: Long) {
        viewModelScope.launch {
            togglePersonFavoriteUseCase(personId)
        }
    }

    fun createNewPerson() {
        viewModelScope.launch {
            createNewPersonUseCase()
        }
    }
}