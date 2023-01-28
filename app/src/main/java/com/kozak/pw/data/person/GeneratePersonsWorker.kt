package com.kozak.pw.data.person


import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kozak.pw.PwApp
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.person.PersonGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeneratePersonsWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val WORK_NAME = "generate persons worker"
    }

    private val repository = PersonItemRepositoryImpl(PwApp.getInstance())

    override fun doWork(): Result {
        try {
            Log.d(PwConstants.LOG_TAG, "GeneratePersonWorker: generating a person.")
            val scope = CoroutineScope(Dispatchers.IO)
            val newPerson = PersonGenerator.generate()
            scope.launch {
                repository.addPerson(newPerson)
                Log.d(PwConstants.LOG_TAG, "Saved new person #${newPerson.fullName()} to DB.")
            }
        } catch (e: Exception) {
            return Result.failure()
        }
        return Result.success()
    }
}
