package com.example.traticket.util

import android.app.Application
import android.content.Context
import android.net.*
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import com.example.traticket.managers.ConnectionManager

//https://android.jlelse.eu/connectivitylivedata-6861b9591bcc

class ConnectivityLiveData internal constructor(private val connectivityManager: ConnectivityManager,
                                                private val isFromNoConnectionsScreen: Boolean,
                                                private val context: Context): LiveData<Boolean>() {
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    constructor(application: Application, isFromNoConnectionsScreen: Boolean, context: Context) :
            this(
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager,
                isFromNoConnectionsScreen,
                context
            )

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            postValue(true)
        }

        override fun onLost(network: Network) {
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
            val networkState = checkNetworkState(context)

        if (ConnectionManager.containsState(context) && !(ConnectionManager.getLastSavedState(context) && networkState && !isFromNoConnectionsScreen)) {
            postValue(networkState)
        }

        ConnectionManager.setState(context, networkState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
    }


    private fun checkNetworkState(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}