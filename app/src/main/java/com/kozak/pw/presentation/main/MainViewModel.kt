package com.kozak.pw.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kozak.pw.BuildConfig
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.game.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainViewModel : ViewModel() {

    private val repository: PwGameRepository by inject(PwGameRepository::class.java)

    private val invokeIsGameStarted = IsGameStartedUseCase(repository)
    private val invokeDestroyCurrentWorld = DestroyCurrentWorldUseCase(repository)
    private val invokeStartNewGame = StartNewGameUseCase(repository)

    private val _startNewGameResult = MutableLiveData<Boolean?>()
    val startNewGameResult: LiveData<Boolean?>
        get() = _startNewGameResult

    /**
     * Set value to null to avoid immediately trigger Observer.onChanged() method, when LiveData has
     * has any value and LiveData.observe() was invoked
     */
    fun afterUseStartNewGameResult() {
        _startNewGameResult.value = null
    }

    private val _gameStarted = MutableLiveData<Boolean>()
    val gameStarted: LiveData<Boolean>
        get() = _gameStarted

    private val _destroyCurrentWorldResult = MutableLiveData<Boolean?>()
    val destroyCurrentWorldResult: LiveData<Boolean?>
        get() = _destroyCurrentWorldResult

    /**
     * Set value to null to avoid immediately trigger Observer.onChanged() method, when LiveData has
     * has any value and LiveData.observe() was invoked
     */
    fun afterUseDestroyCurrentWorldResult() {
        _destroyCurrentWorldResult.value = null
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _appVersion = MutableLiveData<String>()
    val appVersion: LiveData<String>
        get() = _appVersion

    fun retrieveAppVersion() {
        _appVersion.value = BuildConfig.VERSION_NAME
    }

    fun refreshGameStarted() {
        viewModelScope.launch {
            val gameStarted = invokeIsGameStarted()
            _gameStarted.value = gameStarted
        }
    }

    fun startNewGame(gameSpeed: GameSpeed) {
        _isLoading.value = true
        Log.d(PwConstants.LOG_TAG, "start Loading...")

        viewModelScope.launch {
            if (!invokeIsGameStarted()) {
                val gameStarted = invokeStartNewGame(gameSpeed)
                _startNewGameResult.postValue(gameStarted)

                Log.d(PwConstants.LOG_TAG, "stop Loading...")
                _isLoading.postValue(false)
            } else {
                _startNewGameResult.postValue(false)
                _isLoading.postValue(false)
            }
        }
    }

    fun destroyCurrentWorld() {
        viewModelScope.launch {
            invokeDestroyCurrentWorld()
            _destroyCurrentWorldResult.value = true
            _gameStarted.value = false
        }
    }
}