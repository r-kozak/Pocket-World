package com.kozak.pw.data

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kozak.pw.PwApp
import com.kozak.pw.PwConstants
import com.kozak.pw.WorkerUtil
import com.kozak.pw.data.news.NewsRepositoryImpl

class NewsNotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val WORK_NAME = "show notification with news"
    }

    // TODO add Koin injection
    private val newsRepository = NewsRepositoryImpl(PwApp.getInstance())

    override fun doWork(): Result {
        try {
            Log.d(PwConstants.LOG_TAG, "NewsNotificationWorker: retrieving news...")

            val randomNews = newsRepository.getNewsList().value?.random()?.let {
                // show notification with random news
                WorkerUtil.makeStatusNotification(
                    it.title,
                    it.text,
                    applicationContext
                )
            }
        } catch (e: Exception) {
            return Result.failure()
        }
        return Result.success()
    }
}
