package com.example.traticket.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.traticket.managers.LocaleManager
import com.example.traticket.util.ConnectivityLiveData

open class BaseActivity: AppCompatActivity() {
    private lateinit var connectionLiveData: ConnectivityLiveData

    override fun attachBaseContext(newBase: Context?) {
        //load chosen language
        super.attachBaseContext(newBase?.let { LocaleManager.setLocale(it) })
    }

    fun setConnectivityLiveData(screenName: String){
        
    }
}