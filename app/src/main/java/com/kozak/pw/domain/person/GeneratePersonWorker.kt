package com.kozak.pw.domain.person

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kozak.pw.PwConstants

class GeneratePersonWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val WORK_NAME: String = "generate persons in background"

    }

    override fun doWork(): Result {
        try {
            Log.d(PwConstants.LOG_TAG, "GeneratePersonWorker: generating a person.")
        } catch (e: Exception) {
            return Result.retry()
        }
        return Result.success()
    }
}