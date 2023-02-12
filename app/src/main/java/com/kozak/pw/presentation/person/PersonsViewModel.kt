package com.kozak.pw.presentation.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kozak.pw.domain.person.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class PersonsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PersonRepository by inject(PersonRepository::class.java)

    private val invokeGetPersons = GetPersonsListUseCase(repository)
    private val invokeKillPerson = KillPersonUseCase(repository)
    private val invokeTogglePersonFavorite = TogglePersonFavoriteUseCase(repository)
    private val invokeCreateNewPerson = CreateNewPersonUseCase(repository)

    val personsList = invokeGetPersons()

    fun killPerson(personId: Long) {
        viewModelScope.launch {
            invokeKillPerson(personId)
        }
    }

    fun togglePersonFavorite(personId: Long) {
        viewModelScope.launch {
            invokeTogglePersonFavorite(personId)
        }
    }

    fun createNewPerson() {
        viewModelScope.launch {
            invokeCreateNewPerson()
        }
    }
}