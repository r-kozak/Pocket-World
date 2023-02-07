package com.kozak.pw.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.kozak.pw.BuildConfig
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.person.GeneratePersonsWorker
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _pwStateRefreshed = MutableLiveData<Boolean>()
    val pwStateRefreshed: LiveData<Boolean>
        get() = _pwStateRefreshed

    private val _appVersion = MutableLiveData<String>()
    val appVersion: LiveData<String>
        get() = _appVersion

    private val _failToGeneratePerson = MutableLiveData<Boolean>()
    val failToGeneratePerson: LiveData<Boolean>
        get() = _failToGeneratePerson

    private var workManagerObserver: Observer<WorkInfo> = Observer { workInfo ->
        when (workInfo?.state) {
            WorkInfo.State.FAILED -> {
                _failToGeneratePerson.value = true
                _pwStateRefreshed.value = true // to hide progress bar
            }
            WorkInfo.State.SUCCEEDED -> {
                _pwStateRefreshed.value = true
                _failToGeneratePerson.value = false
            }
            else -> Log.d(PwConstants.LOG_TAG, "GeneratePersonWorker state: ${workInfo?.state}")
        }
    }

    private val workManager = WorkManager.getInstance(app.applicationContext)
    private var workRequestsIds = mutableListOf<UUID>()

    fun refreshPwState() {
        viewModelScope.launch {
            _pwStateRefreshed.value = false

            val workRequest = OneTimeWorkRequest
                .Builder(GeneratePersonsWorker::class.java)
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()

            val workRequestId = workRequest.id
            workRequestsIds.add(workRequest.id)
            Log.d(PwConstants.LOG_TAG, "To list added workRequestId: $workRequestId")

            workManager.enqueue(workRequest)
            workManager.getWorkInfoByIdLiveData(workRequestId).observeForever(workManagerObserver)
        }
    }

    fun retrieveAppVersion() {
        _appVersion.value = BuildConfig.VERSION_NAME
    }

    override fun onCleared() {
        super.onCleared()
        workRequestsIds.forEach {
            workManager.getWorkInfoByIdLiveData(it).removeObserver(workManagerObserver)
            Log.d(PwConstants.LOG_TAG, "Removed observer for workRequestId: $it")
        }
    }
}