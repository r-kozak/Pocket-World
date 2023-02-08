package com.kozak.pw.domain.news

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.TaskStackBuilder
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.utils.WorkerUtil
import com.kozak.pw.presentation.news.NewsActivity
import org.koin.java.KoinJavaComponent.inject

class NewsNotificationWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "show notification with news"
    }

    private val newsRepository: NewsRepository by inject(NewsRepository::class.java)

    override suspend fun doWork(): Result {
        try {
            // create intent to open NewsActivity on notification tap
            val intent = Intent(applicationContext, NewsActivity::class.java)
            val pendingIntent: PendingIntent? = TaskStackBuilder.create(applicationContext).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(intent)
                // Get the PendingIntent containing the entire back stack
                getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }

            Log.d(PwConstants.LOG_TAG, "NewsNotificationWorker: retrieving news...")
            // live data list doesn't work in worker use simple list
            newsRepository.getNewsList().random().let { randomNews ->
                // show notification with a random news
                WorkerUtil.makeStatusNotification(
                    randomNews.title,
                    randomNews.text,
                    applicationContext,
                    pendingIntent
                )
            }
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.message ?: "Error occurred in NewsNotificationWorker")
            return Result.failure()
        }
        return Result.success()
    }
}
