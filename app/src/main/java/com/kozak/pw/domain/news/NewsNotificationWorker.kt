package com.kozak.pw.domain.news

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.TaskStackBuilder
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.utils.WorkerUtil
import com.kozak.pw.presentation.news.NewsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class NewsNotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val WORK_NAME = "show notification with news"
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val newsRepository: NewsRepository by inject(NewsRepository::class.java)

    override fun doWork(): Result {
        try {
            // create intent to open NewsActivity on notification tap
            val intent = Intent(applicationContext, NewsActivity::class.java)
            val pendingIntent: PendingIntent? = TaskStackBuilder.create(applicationContext).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(intent)
                // Get the PendingIntent containing the entire back stack
                getPendingIntent(0,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            }

            coroutineScope.launch {
                Log.d(PwConstants.LOG_TAG, "NewsNotificationWorker: retrieving news...")
                val newsList = newsRepository.getNewsList()
                val lifecycleOwner = ProcessLifecycleOwner.get()
                newsList.observe(lifecycleOwner) {
                    it.random().let { randomNews ->
                        // show notification with a random news
                        WorkerUtil.makeStatusNotification(
                            randomNews.title,
                            randomNews.text,
                            applicationContext,
                            pendingIntent
                        )
                        newsList.removeObservers(lifecycleOwner)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(PwConstants.LOG_TAG, e.message ?: "Error occurred in NewsNotificationWorker")
            return Result.failure()
        }
        return Result.success()
    }
}
