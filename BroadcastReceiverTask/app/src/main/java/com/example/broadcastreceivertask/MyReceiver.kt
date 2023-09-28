package com.example.broadcastreceivertask

import android.app.Activity
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let{
            val status = NetworkUtil.getNetworkState(context)

            val dialog = Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen)

            dialog.setContentView(R.layout.custom_dialog)

            val btnRetry: Button = dialog.findViewById(R.id.btn_retry)

            btnRetry.setOnClickListener {
                (context as Activity).finish()

                val intentToActivity = Intent(context, MainActivity::class.java)

                context.startActivity(intentToActivity)
            }

            if(status.isEmpty() || status == "No internet connected"){
                dialog.show()
            }

            Toast.makeText(context, status, Toast.LENGTH_LONG).show()
        }
    }
}