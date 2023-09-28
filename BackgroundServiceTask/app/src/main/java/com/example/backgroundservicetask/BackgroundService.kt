package com.example.backgroundservicetask

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class BackgroundService : Service() {

    // declaring object of media player
    lateinit var player: MediaPlayer

    // Execution of service will start on calling this method
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // creating media player
        // which will play the audio of default ringtone
        // in android device
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)

        // providing the boolean value
        // as true
        // to play the audio on loop
        //player.setLooping(true)
        player.isLooping = true

        // starting the process
        player.start()

        // returns the status
        // of the program
        return START_STICKY
    }

    // execution of service
    // will stop
    // on calling this method
    override fun onDestroy() {
        super.onDestroy()

        // stopping the process
        player.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}