package com.kozak.pw.presentation.dashboard

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.kozak.pw.PwApp
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.game.IsGameStartedUseCase
import com.kozak.pw.domain.game.PwGameRepository
import com.kozak.pw.domain.game.StartNewGameWorker
import com.kozak.pw.domain.person.GeneratePersonsWorker
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

    private var startGameObserver: Observer<WorkInfo> = Observer { workInfo ->
        when (workInfo?.state) {
            WorkInfo.State.FAILED -> {
                Log.d(PwConstants.LOG_TAG, "StartNewGameWorker: FAILED")
                _failToStartNewGame.value = true
                _isLoading.value = false // to hide progress bar
            }
            WorkInfo.State.SUCCEEDED -> {
                Log.d(PwConstants.LOG_TAG, "StartNewGameWorker: SUCCEEDED")
                _isLoading.value = false
                _failToStartNewGame.value = false
            }
            else -> Log.d(PwConstants.LOG_TAG, "StartNewGameWorker state: ${workInfo?.state}")
        }
    }

    private val workManager = WorkManager.getInstance(PwApp.getInstance().applicationContext)
    private var refreshPwStateRequestsIds = mutableListOf<UUID>()
    private lateinit var startGameRequestId: UUID

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

    fun startNewGame() {
        viewModelScope.launch {
            val gameStarted = isGameStartedUseCase()
            if (!gameStarted) {
                _isLoading.value = true

                val workRequest = OneTimeWorkRequest
                    .Builder(StartNewGameWorker::class.java)
                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .build()

                val workRequestId = workRequest.id
                startGameRequestId = workRequestId
                Log.d(PwConstants.LOG_TAG, "To list added workRequestId: $workRequestId")

                workManager.enqueue(workRequest)
                workManager.getWorkInfoByIdLiveData(workRequestId).observeForever(startGameObserver)
            } else {
                _failToStartNewGame.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        refreshPwStateRequestsIds.forEach {
            workManager.getWorkInfoByIdLiveData(it).removeObserver(refreshPwStateObserver)
            Log.d(PwConstants.LOG_TAG, "Removed observer for workRequestId: $it")
        }
        if (this::startGameRequestId.isInitialized) {
            workManager.getWorkInfoByIdLiveData(startGameRequestId).removeObserver(startGameObserver)
        }
    }
}