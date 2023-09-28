package com.example.foregroundservicetask

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    var mSong: Song? = null
    var isPlaying: Boolean = false

    private var broadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val bundle: Bundle = intent?.extras ?: return

            mSong = bundle.get(RunningService.OBJECT_SONG) as Song
            isPlaying = bundle.getBoolean(RunningService.STATUS_MUSIC)
            val actionMusic: Int = bundle.getInt(RunningService.MUSIC_ACTION)

            handleLayoutMusic(actionMusic)
        }
    }

    lateinit var layoutBottom: RelativeLayout
    lateinit var imgSong: ImageView
    lateinit var imgPlayOrPause: ImageView
    lateinit var imgClose: ImageView
    lateinit var tvSingleSong: TextView
    lateinit var tvTitleSong: TextView

    companion object{
        const val SONG_NOTIFICATION_ID = "song_notification_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        layoutBottom = findViewById(R.id.layout_bottom)
        imgSong = findViewById(R.id.img_song)
        imgPlayOrPause = findViewById(R.id.img_play_or_pause)
        imgClose = findViewById(R.id.img_close)
        tvSingleSong = findViewById(R.id.tv_single_song)
        tvTitleSong = findViewById(R.id.tv_title_song)

        var btnStartService: Button = findViewById(R.id.btn_start_service)
        var btnStopService: Button = findViewById(R.id.btn_stop_service)

        // Sign up Broadcast Receiver to receive data from LocalBroadcastManager
        LocalBroadcastManager
            .getInstance(this)
            .registerReceiver(
                broadcastReceiver,
                IntentFilter(RunningService.SEND_DATA_TO_ACTIVITY)
            )

        btnStartService.setOnClickListener {
            val song = Song(
                "PAiNT it BLACK",
                "BiSH",
                R.drawable.blackclover_img,
                R.raw.black_clover_op2
            )

            val bundle = Bundle()

            bundle.putSerializable(SONG_NOTIFICATION_ID, song)

            val intent = Intent(applicationContext, RunningService::class.java)

            intent.putExtras(bundle)
            intent.action = RunningService.Actions.START.toString()

            startService(intent)
        }

        btnStopService.setOnClickListener {
            val intent = Intent(applicationContext, RunningService::class.java)

            intent.action = RunningService.Actions.STOP.toString()

            startService(intent)
            //stopService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager
            .getInstance(this)
            .unregisterReceiver(broadcastReceiver)
    }

    private fun handleLayoutMusic(action: Int){
        when(action){
            RunningService.ACTION_START -> {
                layoutBottom.visibility = View.VISIBLE
                showInfoSong()
                setStatusButtonPlayOrPause()
            }
            RunningService.ACTION_PAUSE -> setStatusButtonPlayOrPause()
            RunningService.ACTION_RESUME -> setStatusButtonPlayOrPause()
            RunningService.ACTION_CLOSE -> layoutBottom.visibility = View.GONE
        }
    }

    private fun setStatusButtonPlayOrPause(){
        if(isPlaying){
            imgPlayOrPause.setImageResource(R.drawable.img_pause)
        }else{
            imgPlayOrPause.setImageResource(R.drawable.img_resume)
        }

        imgPlayOrPause.setOnClickListener {
            if(isPlaying){
                // Send a request to the service to request a pause
                sendActionToService(RunningService.ACTION_PAUSE)
            }else{
                // Send a request to the service to request a resume
                sendActionToService(RunningService.ACTION_RESUME)
            }
        }

        imgClose.setOnClickListener {
            sendActionToService(RunningService.ACTION_CLOSE)
        }
    }

    private fun showInfoSong(){
        if(mSong == null){
            return
        }

        imgSong.setImageResource(mSong!!.image)
        tvTitleSong.text = mSong!!.title
        tvSingleSong.text = mSong!!.single
    }

    private fun sendActionToService(action: Int){
        val intent: Intent = Intent(this, RunningService::class.java)

        intent.putExtra(RunningService.ACTION_MUSIC_SERVICE, action)

        startService(intent)
    }
}
