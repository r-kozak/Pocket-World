package com.kozak.pw.presentation.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.kozak.pw.PwApp
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.game.GameSpeed
import com.kozak.pw.domain.game.IsGameStartedUseCase
import com.kozak.pw.domain.game.PwGameRepository
import com.kozak.pw.domain.game.StartNewGameUseCase
import com.kozak.pw.domain.person.GeneratePersonsWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.util.*

class DashboardViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _failToRefreshPwState = MutableLiveData<Boolean>()
    val failToRefreshPwState: LiveData<Boolean>
        get() = _failToRefreshPwState

    private val _failToStartNewGame = MutableLiveData<Boolean>()
    val failToStartNewGame: LiveData<Boolean>
        get() = _failToStartNewGame

    private val repository: PwGameRepository by inject(PwGameRepository::class.java)
    private val isGameStartedUseCase = IsGameStartedUseCase(repository)
    private val startNewGameUseCase = StartNewGameUseCase(repository)

    private var refreshPwStateObserver: Observer<WorkInfo> = Observer { workInfo ->
        when (workInfo?.state) {
            WorkInfo.State.FAILED -> {
                _failToRefreshPwState.value = true
                _isLoading.value = false // to hide progress bar
            }
            WorkInfo.State.SUCCEEDED -> {
                _isLoading.value = false
                _failToRefreshPwState.value = false
            }
            else -> Log.d(PwConstants.LOG_TAG, "GeneratePersonWorker state: ${workInfo?.state}")
        }
    }

    private val workManager = WorkManager.getInstance(PwApp.getInstance().applicationContext)
    private var refreshPwStateRequestsIds = mutableListOf<UUID>()

    fun refreshPwState() {
        _isLoading.value = true

        val workRequest = OneTimeWorkRequest
            .Builder(GeneratePersonsWorker::class.java)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()

        val workRequestId = workRequest.id
        refreshPwStateRequestsIds.add(workRequestId)
        Log.d(PwConstants.LOG_TAG, "To list added workRequestId: $workRequestId")

        workManager.enqueue(workRequest)
        workManager.getWorkInfoByIdLiveData(workRequestId).observeForever(refreshPwStateObserver)
    }

    fun startNewGame(gameSpeed: GameSpeed) {
        _isLoading.value = true
        Log.d(PwConstants.LOG_TAG, "start Loading...")

        CoroutineScope(Dispatchers.IO).launch {
            if (!isGameStartedUseCase()) {
                val gameStarted = startNewGameUseCase(gameSpeed)
                _failToStartNewGame.postValue(!gameStarted)

                Log.d(PwConstants.LOG_TAG, "stop Loading...")
                _isLoading.postValue(false)
            } else {
                _failToStartNewGame.postValue(true)
                _isLoading.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        refreshPwStateRequestsIds.forEach {
            workManager.getWorkInfoByIdLiveData(it).removeObserver(refreshPwStateObserver)
            Log.d(PwConstants.LOG_TAG, "Removed observer for workRequestId: $it")
        }
    }
}