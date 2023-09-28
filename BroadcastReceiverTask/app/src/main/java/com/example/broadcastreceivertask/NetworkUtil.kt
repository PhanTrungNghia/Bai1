package com.example.broadcastreceivertask

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtil {

    companion object{
        fun getNetworkState(context: Context): String{
            var status: String? = null
            val connectivityManager: ConnectivityManager
            = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

            return when{
                networkInfo == null || !networkInfo.isConnected ->{
                    "No internet connected"
                }
                networkInfo.type == ConnectivityManager.TYPE_WIFI -> {
                    "Wifi connected"
                }
                networkInfo.type == ConnectivityManager.TYPE_MOBILE -> {
                    "Mobile data connected"
                }
                else -> {
                    "Other network connected"
                }

            }
        }
    }
}