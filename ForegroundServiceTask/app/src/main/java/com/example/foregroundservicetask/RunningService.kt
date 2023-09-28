package com.example.foregroundservicetask

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews

import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class RunningService : Service() {

    companion object{
        const val ACTION_PAUSE = 1
        const val ACTION_RESUME = 2
        const val ACTION_CLOSE = 3
        const val ACTION_START = 4

        const val ACTION_MUSIC = "action_music"
        const val ACTION_MUSIC_SERVICE = "action_music_service"
        const val SEND_DATA_TO_ACTIVITY = "send_data_to_activity"

        const val OBJECT_SONG = "object_song"
        const val STATUS_MUSIC = "status_music"
        const val MUSIC_ACTION = "music_action"
    }

    var mediaPlayer: MediaPlayer? = null
    var isPlaying: Boolean = true
    lateinit var mSong: Song

    override fun onCreate() {
        super.onCreate()
        Log.e("RunningService", "RunningService onCreate")
    }

    // Function for creating Bound Service
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val bundle = intent?.extras

        //TODO: Check for different action of intent
        when(intent?.action){
            Actions.START.toString() -> bundle.let {
                val song: Song = bundle?.get(MainActivity.SONG_NOTIFICATION_ID) as Song
                mSong = song
                start(song)
                startMusic(song)
            }
            Actions.STOP.toString() -> stopSelf()
        }

        val actionMusic: Int = intent!!.getIntExtra(ACTION_MUSIC_SERVICE, 0)

        handleActionMusic(actionMusic)

        return START_NOT_STICKY
    }

    private fun handleActionMusic(action: Int){
        when(action){
            ACTION_PAUSE -> pauseMusic()
            ACTION_RESUME -> resumeMusic()
            ACTION_CLOSE -> {
                stopSelf()
                sendDataToActivity(ACTION_CLOSE)
            }
        }
    }

    private fun resumeMusic() {
        mediaPlayer.let {
            if(!isPlaying){
                mediaPlayer?.start()
                isPlaying = true
                start(mSong)

                sendDataToActivity(ACTION_RESUME)
            }
        }
    }

    private fun pauseMusic() {
        mediaPlayer.let {
            if(isPlaying){
                mediaPlayer?.pause()
                isPlaying = false
                start(mSong)

                sendDataToActivity(ACTION_PAUSE)
            }
        }
    }

    private fun startMusic(song: Song) {
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(applicationContext, song.resource )
        }

        mediaPlayer?.isLooping = true

        mediaPlayer?.start()

        isPlaying = true

        sendDataToActivity(ACTION_START)
    }

    private fun start(song: Song){
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, song.image)

        val remoteViews = RemoteViews(packageName, R.layout.layout_custom_notification)

        remoteViews.setTextViewText(R.id.tv_title_song, song.title)
        remoteViews.setTextViewText(R.id.tv_single_song, song.single)
        remoteViews.setImageViewBitmap(R.id.img_song, bitmap)
        remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.img_pause)
        remoteViews.setImageViewResource(R.id.img_close, R.drawable.img_close)


        // Part of handling click event's views on custom notification.
        if(isPlaying){
            remoteViews.setOnClickPendingIntent(R.id.img_play_or_pause, getPendingIntent(this, ACTION_PAUSE))
            remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.img_pause)
        }else{
            remoteViews.setOnClickPendingIntent(R.id.img_play_or_pause, getPendingIntent(this, ACTION_RESUME))
            remoteViews.setImageViewResource(R.id.img_play_or_pause, R.drawable.img_resume)
        }

        remoteViews.setOnClickPendingIntent(R.id.img_close, getPendingIntent(this, ACTION_CLOSE))

        // Initializing PendingIntent
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE //Use FLAG_MUTABLE since you are updating the PendingIntent.
        )

        // Initializing notification
        val notification = NotificationCompat.Builder(this, RunningApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setContentTitle("Run is active")
            .setCustomContentView(remoteViews)
            .setSound(null)
            .setContentIntent(pendingIntent)
            .build()

        // Starting foreground with custom notification
        // Foreground must request permission from system service to start
        startForeground(1, notification)
    }

    private fun getPendingIntent(context: Context, action: Int): PendingIntent? {
        val intent = Intent(context, MyReceiver::class.java)

        intent.putExtra(ACTION_MUSIC, action)

        return PendingIntent.getBroadcast(
            applicationContext,
            action,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
    }

    // Method for sending information of notification and flags, action of music to activity
    private fun sendDataToActivity(action: Int){
        val intent = Intent(SEND_DATA_TO_ACTIVITY)
        val bundle = Bundle()

        bundle.putSerializable(OBJECT_SONG, mSong)
        bundle.putBoolean(STATUS_MUSIC, isPlaying)
        bundle.putInt(MUSIC_ACTION, action)

        intent.putExtras(bundle)

        // Initializing LocalBroadcastManager for sending intent
        LocalBroadcastManager
            .getInstance(this)
            .sendBroadcast(intent)
    }

    // Define different action for intent
    enum class Actions{
        START, STOP
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("RunningService", "RunningService onDestroy")

        if(mediaPlayer != null){
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}