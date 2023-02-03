package com.kozak.pw.data.person

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kozak.pw.PwApp
import com.kozak.pw.PwConstants
import com.kozak.pw.WorkerUtil
import com.kozak.pw.domain.person.PersonGenerator

class GeneratePersonsWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "generate persons worker"
    }

    private val repository = PersonRepositoryImpl(PwApp.getInstance())

    override suspend fun doWork(): Result {
        try {
            Log.d(PwConstants.LOG_TAG, "GeneratePersonWorker: generating a person.")
            val newPerson = PersonGenerator.generate()
            repository.addPerson(newPerson)

            val generatedPersonName = newPerson.fullName()
            Log.d(PwConstants.LOG_TAG, "Saved the person $generatedPersonName to DB.")

            // show notification that person was born (generated)
            WorkerUtil.makeStatusNotification(
                "Person $generatedPersonName was born",
                applicationContext
            )
        } catch (e: Exception) {
            return Result.failure()
        }
        return Result.success()
    }
}
