package com.kozak.pw.domain.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kozak.pw.PwConstants
import com.kozak.pw.R

class WorkerUtil {
    companion object {
        private const val VERBOSE_NOTIFICATION_CHANNEL_NAME = "PW notifications channel"
        private const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION = "PW notifications description"
        private const val CHANNEL_ID = "PW-channel-id"
        private const val NOTIFICATION_ID = 257
        private const val ONE_SECOND_IN_MILLIS = 1000

        /**
         * Create a Notification that is shown as a heads-up notification if possible.
         *
         * @param notificationTitle Title of notification
         * @param message Message shown on the notification
         * @param context Context needed to create Toast
         * @param pendingIntent intent to open activity on notification tap. If null - no action
         * will be performed on tap
         */
        @SuppressLint("MissingPermission")
        fun makeStatusNotification(
            notificationTitle: CharSequence?,
            message: String,
            context: Context,
            pendingIntent: PendingIntent? = null
        ) {
            // Make a channel if necessary
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
            val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description

            // Add the channel
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
            notificationManager?.createNotificationChannel(channel)

            // Create the notification
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(notificationTitle)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(LongArray(0))
                .setAutoCancel(true)

            pendingIntent?.let {
                // set intent to open activity on notification tap
                builder.setContentIntent(it)
            }
            // Show the notification
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        }

        /**
         * Method for sleeping for a fixed about of time to emulate slower work
         */
        fun sleep(durationInSec: Long) {
            try {
                Thread.sleep(ONE_SECOND_IN_MILLIS * durationInSec, 0)
            } catch (e: InterruptedException) {
                Log.e(PwConstants.LOG_TAG, e.message!!)
            }
        }
    }
}
