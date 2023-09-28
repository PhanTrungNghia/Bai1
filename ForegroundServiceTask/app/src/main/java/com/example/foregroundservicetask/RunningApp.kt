package com.example.foregroundservicetask

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class RunningApp : Application() {
    companion object{
        const val CHANNEL_ID: String = "running_channel"
    }

    override fun onCreate() {
        super.onCreate()

        createChannelNotification()
    }

    private fun createChannelNotification() {

        val channelName = getString(R.string.channel_name)
        val channelImportance = NotificationManager.IMPORTANCE_HIGH

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                channelName,
                channelImportance
            )

            channel.setSound(null, null)

            // Notification Manager for displaying notification
            // Method getSystemService to get service of system
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.let {
                // Initializing channel
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}