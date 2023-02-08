package com.kozak.pw.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kozak.pw.BuildConfig

class MainViewModel : ViewModel() {

    private val _appVersion = MutableLiveData<String>()
    val appVersion: LiveData<String>
        get() = _appVersion

    fun retrieveAppVersion() {
        _appVersion.value = BuildConfig.VERSION_NAME
    }
}