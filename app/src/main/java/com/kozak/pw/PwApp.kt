package com.kozak.pw

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.kozak.pw.data.person.GeneratePersonsWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class PwApp : Application() {

    companion object {
        private lateinit var instance: Application

        fun getInstance(): Application {
            return instance
        }
    }

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate() {
        Log.d(PwConstants.LOG_TAG, "PwApp: onCreate().")
        super.onCreate()
        instance = this

        doBackgroundPocketWorldChanges()
        // TODO uncomment when implemented:
        // doLifeInPw()
    }

    /**
     * Performs life processes in over the Pocket World
     */
    private fun doLifeInPw() {
        // TODO: implement
        val handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {
                try {
                    // TODO: give birth to children, make peace and war and other stuff
                    // Log.d(PwConstants.LOG_TAG, "PwApp: doLifeInPw().")
                } catch (e: Exception) {
                    // TODO: handle exception
                } finally {
                    // also call the same runnable to call it at regular interval
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(runnable)
    }

    /**
     * Runs service that do its work when device is idle.
     */
    private fun doBackgroundPocketWorldChanges() = applicationScope.launch {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresDeviceIdle(true)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<GeneratePersonsWorker>(
            PwConstants.GENERATE_PERSON_EVERY_X_MINUTES,
            TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            GeneratePersonsWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}