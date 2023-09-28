package com.example.broadcastreceivertask

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var mReceiver: MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mReceiver = MyReceiver()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(mReceiver)
    }
}