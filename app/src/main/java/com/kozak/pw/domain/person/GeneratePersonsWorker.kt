package com.kozak.pw.domain.person

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.news.News
import com.kozak.pw.domain.news.NewsRepository
import org.koin.java.KoinJavaComponent.inject

class GeneratePersonsWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val personRepository: PersonRepository by inject(PersonRepository::class.java)
    private val newsRepository: NewsRepository by inject(NewsRepository::class.java)

    override suspend fun doWork(): Result {
        try {
            Log.d(PwConstants.LOG_TAG, "GeneratePersonWorker: generating a person.")
            val newPerson = PersonGenerator.generate()
            personRepository.insert(newPerson)

            val generatedPersonName = newPerson.fullName()
            Log.d(PwConstants.LOG_TAG, "Saved the person $generatedPersonName to DB.")

            // create a news that person was born
            val news = News(
                "Person was born",
                "Person '$generatedPersonName' was born.\nFull info: $newPerson",
                News.NewsPriority.MIDDLE
            )
            newsRepository.insert(news)
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.message ?: "Error occurred in GeneratePersonsWorker")
            return Result.failure()
        }
        return Result.success()
    }
}
