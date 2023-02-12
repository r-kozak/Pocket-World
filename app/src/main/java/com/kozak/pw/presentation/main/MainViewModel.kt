package com.kozak.pw.presentation.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kozak.pw.BuildConfig
import com.kozak.pw.domain.game.DestroyCurrentWorldUseCase
import com.kozak.pw.domain.game.IsGameStartedUseCase
import com.kozak.pw.domain.game.PwGameRepository
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    private val repository: PwGameRepository by inject(PwGameRepository::class.java)

    private val invokeIsGameStarted = IsGameStartedUseCase(repository)
    private val invokeDestroyCurrentWorld = DestroyCurrentWorldUseCase(repository)

    private val _gameStarted = MutableLiveData<Boolean>()
    val gameStarted: LiveData<Boolean>
        get() = _gameStarted

    private val _currentWorldDestroyed = MutableLiveData<Boolean>()
    val currentWorldDestroyed: LiveData<Boolean>
        get() = _currentWorldDestroyed

    private val _appVersion = MutableLiveData<String>()
    val appVersion: LiveData<String>
        get() = _appVersion

    fun retrieveAppVersion() {
        _appVersion.value = BuildConfig.VERSION_NAME
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun retrieveGameStarted() {
        viewModelScope.launch {
            _gameStarted.value = invokeIsGameStarted()
        }
    }

    fun destroyCurrentWorld() {
        viewModelScope.launch {
            invokeDestroyCurrentWorld()
            _currentWorldDestroyed.value = true
        }
    }
}