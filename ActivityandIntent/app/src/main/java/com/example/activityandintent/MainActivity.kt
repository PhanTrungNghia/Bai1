package com.example.activityandintent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    companion object{

        const val TAG: String = "MainActivity"
        const val REQUEST_CODE: Int = 9
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "onCreate called")

        Toast.makeText(
            applicationContext,
            "onCreate called",
            Toast.LENGTH_LONG
        ).show()

        val btnClickMe: Button = findViewById(R.id.btnClickMe)

        btnClickMe.setOnClickListener {

            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("key1", "value1 for SecondActivity")
            intent.putExtra("key2", "value2 for SecondActivity")

            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 9 && resultCode == RESULT_OK){
            if(data!!.hasExtra("returnKey1")){
                Toast.makeText(
                    applicationContext,
                    data.getExtras()?.getString("returnKey1"),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
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