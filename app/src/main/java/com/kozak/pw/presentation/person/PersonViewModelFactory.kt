package com.kozak.pw.presentation.person

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PersonViewModelFactory(private val application: Application, private val personId: Long) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(Application::class.java, Long::class.java)
            .newInstance(application, personId)
    }
}