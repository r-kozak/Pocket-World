package com.kozak.pw.data.person

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kozak.pw.PwApp
import com.kozak.pw.PwConstants
import com.kozak.pw.data.news.NewsRepositoryImpl
import com.kozak.pw.domain.news.News
import com.kozak.pw.domain.person.PersonGenerator

class GeneratePersonsWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "generate persons worker"
    }

    // TODO add Koin injection
    private val personRepository = PersonRepositoryImpl(PwApp.getInstance())
    private val newsRepository = NewsRepositoryImpl(PwApp.getInstance())

    override suspend fun doWork(): Result {
        try {
            Log.d(PwConstants.LOG_TAG, "GeneratePersonWorker: generating a person.")
            val newPerson = PersonGenerator.generate()
            personRepository.addPerson(newPerson)

            val generatedPersonName = newPerson.fullName()
            Log.d(PwConstants.LOG_TAG, "Saved the person $generatedPersonName to DB.")

            // create a news that person was born
            val news = News(
                "Person was born",
                "Person '$generatedPersonName' was born.\nFull info: $newPerson",
                News.NewsPriority.MIDDLE
            )
            newsRepository.addNews(news)
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.message ?: "Error occurred in GeneratePersonsWorker")
            return Result.failure()
        }
        return Result.success()
    }
}
