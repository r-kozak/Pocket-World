package com.kozak.pw.presentation.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kozak.pw.domain.person.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class PersonsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PersonRepository by inject(PersonRepository::class.java)

    private val getPersonsUseCase = GetPersonsListUseCase(repository)
    private val killPersonUseCase = KillPersonUseCase(repository)
    private val togglePersonFavoriteUseCase = TogglePersonFavoriteUseCase(repository)
    private val createNewPersonUseCase = CreateNewPersonUseCase(repository)

    val personsList = getPersonsUseCase()

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