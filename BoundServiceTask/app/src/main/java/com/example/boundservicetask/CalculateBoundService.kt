package com.example.boundservicetask

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class CalculateBoundService: Service() {

    private val mBinder = MyBinder()

    inner class MyBinder: Binder(){
        fun getCalculateBoundService(): CalculateBoundService{
            return this@CalculateBoundService
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("CalculateBoundService", "onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("CalculateBoundService", "onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("CalculateBoundService", "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e("CalculateBoundService", "onDestroy")
        super.onDestroy()
    }

    fun sum(numberOne: Double, numberTwo: Double): Double{
        return numberOne + numberTwo
    }
}