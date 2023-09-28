package com.example.activityandintent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object{

        const val TAG: String = "SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.e(TAG, "onCreate called")

        Toast.makeText(
            applicationContext,
            "onCreate called",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun finish() {

        val data = Intent()

        data.putExtra("returnKey1", "returnValue1 for MainActivity")
        data.putExtra("returnKey2", "returnValue2 for MainActivity")

        setResult(RESULT_OK, data)

        super.finish()
    }

    override fun onStart(){
        super.onStart()

        Log.e(TAG, "onStart called")

        Toast.makeText(
            applicationContext,
            "onStart called",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onRestart(){
        super.onRestart()

        Log.e(TAG, "onRestart called")

        Toast.makeText(
            applicationContext,
            "onRestart called",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onResume(){
        super.onResume()

        Log.e(TAG, "onResume called")

        Toast.makeText(
            applicationContext,
            "onResume called",
            Toast.LENGTH_LONG
        ).show()
    }


    override fun onPause(){
        super.onPause()

        Log.e(TAG, "onPause called")

        Toast.makeText(
            applicationContext,
            "onPause called",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onStop(){
        super.onStop()

        Log.e(TAG, "onStop called")

        Toast.makeText(
            applicationContext,
            "onStop called",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroy(){
        super.onDestroy()

        Log.e(TAG, "onDestroy called")

        Toast.makeText(
            applicationContext,
            "onDestroy called",
            Toast.LENGTH_LONG
        ).show()
    }
}