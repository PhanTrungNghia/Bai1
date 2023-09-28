package com.example.backgroundservicetask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // declaring object of button class
    private var btnStartButton: Button? = null
    private var btnStopButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assigning ID to the buttons
        btnStartButton = findViewById(R.id.btnStartButton)
        btnStopButton = findViewById(R.id.btnStopButton)

        // declaring listeners for the buttons
        // to make them respond correctly
        // according to the process
        btnStartButton!!.setOnClickListener(this)
        btnStopButton!!.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        // Process to be performed
        // if start button is clicked
        if(view == btnStartButton){

            // starting the service
            startService(Intent(this, BackgroundService::class.java))
        }
        // Process to be performed
        // if stop button is clicked
        else if(view == btnStopButton){

            // stopping the service
            stopService(Intent(this, BackgroundService::class.java))
        }
    }
}