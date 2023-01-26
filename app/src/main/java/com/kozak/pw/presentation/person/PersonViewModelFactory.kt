package com.kozak.pw.presentation.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PersonViewModelFactory(private val personId: Long) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(personId) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}