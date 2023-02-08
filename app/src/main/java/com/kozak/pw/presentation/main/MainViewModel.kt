package com.kozak.pw.presentation.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kozak.pw.BuildConfig
import com.kozak.pw.domain.game.IsGameStartedUseCase
import com.kozak.pw.domain.game.PwGameRepository
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class MainViewModel : ViewModel() {

    private val repository: PwGameRepository by KoinJavaComponent.inject(PwGameRepository::class.java)
    private val isGameStartedUseCase = IsGameStartedUseCase(repository)

    private val _gameStarted = MutableLiveData<Boolean>()
    val gameStarted: LiveData<Boolean>
        get() = _gameStarted

    private val _appVersion = MutableLiveData<String>()
    val appVersion: LiveData<String>
        get() = _appVersion

    fun retrieveAppVersion() {
        _appVersion.value = BuildConfig.VERSION_NAME
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun retrieveGameStarted() {
        viewModelScope.launch {
            _gameStarted.value = isGameStartedUseCase()
        }
    }
}