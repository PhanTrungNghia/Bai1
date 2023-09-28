package com.example.foregroundservicetask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val actionMusic: Int = intent!!.getIntExtra(RunningService.ACTION_MUSIC, 0)

        val intentService = Intent(context, RunningService::class.java)

        intentService.putExtra(RunningService.ACTION_MUSIC_SERVICE, actionMusic)

        context?.startService(intentService)
    }
}