package com.example.boundservicetask

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btnCalculate: Button
    lateinit var edtNumberOne: EditText
    lateinit var edtNumberTwo: EditText
    lateinit var edtSum: EditText
    lateinit var mCalculateBoundService: CalculateBoundService
    private var mBound: Boolean = false

    private val mServiceConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val mBinder = service as CalculateBoundService.MyBinder
            mCalculateBoundService = mBinder.getCalculateBoundService()

            startCalculate(mCalculateBoundService)

            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    private fun startCalculate(mCalculateBoundService: CalculateBoundService) {
        val result: Double = mCalculateBoundService.sum(
            edtNumberOne.text.toString().trim().toDouble(),
            edtNumberTwo.text.toString().trim().toDouble()
        )

        edtSum.setText(result.toString())

        Toast.makeText(applicationContext, "Calculate Successfully", Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        handleAction()

    }

    private fun handleAction() {
        btnCalculate.setOnClickListener {
            onClickStartService()
        }
    }

    private fun initUI() {
        edtNumberOne = findViewById(R.id.edt_number_one)
        edtNumberTwo = findViewById(R.id.edt_number_two)
        edtSum = findViewById(R.id.edt_sum)
        btnCalculate = findViewById(R.id.btn_calculate)
    }

    private fun onClickStartService() {
        val intent = Intent(this, CalculateBoundService::class.java)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mBound){
            unbindService(mServiceConnection)
            mBound = false
        }
    }
}