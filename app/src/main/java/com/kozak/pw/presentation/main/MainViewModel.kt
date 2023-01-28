package com.kozak.pw.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO implement
class MainViewModel : ViewModel() {
//    private val repository =
//        PersonItemRepositoryImpl(application) // TODO get rid of dependency to data layer

    // private val getPersonUseCase = GetPersonByIdUseCase(repository)

    private val _pwStateRefreshed = MutableLiveData<Boolean>()
    val pwStateRefreshed: LiveData<Boolean>
        get() = _pwStateRefreshed

    fun refreshPwState() {
        viewModelScope.launch {
            _pwStateRefreshed.value = false
            delay(3000)
            _pwStateRefreshed.value = true
        }
    }

}