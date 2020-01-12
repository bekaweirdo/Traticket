package com.example.traticket.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.traticket.managers.SharedPreferencesManager

class SplashScreen: AppCompatActivity(){

    lateinit var sharedPreferences: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLanguageAndCurrency()

    }

    fun checkLanguageAndCurrency(){
        when{

        }
    }
}