package com.kozak.pw.domain.game

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.utils.WorkerUtil

class StartNewGameWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        try {
            Log.d(PwConstants.LOG_TAG, "StartNewGameWorker: generating new pocket world...")
            WorkerUtil.sleep(3)
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.message ?: "Error occurred in StartNewGameWorker")
            return Result.failure()
        }
        return Result.success()
    }
}
