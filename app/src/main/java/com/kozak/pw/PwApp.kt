package com.kozak.pw

import android.app.Application
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.kozak.pw.domain.person.GeneratePersonWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class PwApp : Application() {

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        Log.d(PwConstants.LOG_TAG, "PwApp: onCreate().")

        super.onCreate()
        doBackgroundCPocketWorldChanges()
    }

    private fun doBackgroundCPocketWorldChanges() = applicationScope.launch {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<GeneratePersonWorker>(
            PwConstants.GENERATE_PERSON_EVERY_X_MINUTES,
            TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            GeneratePersonWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}