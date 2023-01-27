package com.kozak.pw.presentation.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kozak.pw.data.person.PersonItemRepositoryImpl
import com.kozak.pw.domain.person.EditPersonUseCase
import com.kozak.pw.domain.person.GetPersonByIdUseCase
import com.kozak.pw.domain.person.KillPersonUseCase
import com.kozak.pw.domain.person.PersonItem
import kotlinx.coroutines.launch

class PersonViewModel(application: Application, personId: Long) : AndroidViewModel(application) {
    private val repository =
        PersonItemRepositoryImpl(application) // TODO get rid of dependency to data layer

    private val getPersonUseCase = GetPersonByIdUseCase(repository)
    private val killPersonUseCase = KillPersonUseCase(repository)
    private val editPersonUseCase = EditPersonUseCase(repository)

    private val _personItem = MutableLiveData<PersonItem>()
    val personItem: LiveData<PersonItem>
        get() = _personItem

    private val _errorInputFirstName = MutableLiveData<Boolean>()
    val errorInputFirstName: LiveData<Boolean>
        get() = _errorInputFirstName

    private val _errorInputLastName = MutableLiveData<Boolean>()
    val errorInputLastName: LiveData<Boolean>
        get() = _errorInputLastName

    private val _errorInputStrength = MutableLiveData<Boolean>()
    val errorInputStrength: LiveData<Boolean>
        get() = _errorInputStrength


    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    init {
        getPersonItem(personId)
    }

    private fun getPersonItem(personId: Long) {
        viewModelScope.launch {
            getPersonUseCase(personId).let { _personItem.value = it }
        }
    }

    fun killPerson(personId: Long) {
        viewModelScope.launch {
            killPersonUseCase(personId)
            finishWork()
        }
    }

    fun editPersonItem(
        id: Long,
        inputFirstName: String?,
        inputLastName: String?,
        inputStrength: String?
    ) {
        val firstName = parseName(inputFirstName)
        val lastName = parseName(inputLastName)
        val strength = parseStrength(inputStrength)
        val fieldsValid = validateInput(firstName, lastName, strength)
        if (fieldsValid) {
            viewModelScope.launch {
                val personItem = getPersonUseCase(id)
                personItem.apply {
                    this.firstName = firstName
                    this.lastName = lastName
                    this.strength = strength
                }
                editPersonUseCase(personItem)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseStrength(inputStrength: String?): Int {
        return try {
            inputStrength?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(firstName: String, lastName: String, strength: Int): Boolean {
        var result = true
        if (firstName.isBlank()) {
            _errorInputFirstName.value = true
            result = false
        }
        if (lastName.isBlank()) {
            _errorInputLastName.value = true
            result = false
        }
        if (strength <= 0) {
            _errorInputStrength.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputFirstName() {
        _errorInputFirstName.value = false
    }

    fun resetErrorInputLastName() {
        _errorInputLastName.value = false
    }

    fun resetErrorInputStrength() {
        _errorInputStrength.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}