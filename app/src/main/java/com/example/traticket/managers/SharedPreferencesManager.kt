package com.example.traticket.managers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context){
    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    init {
        mPref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
        mEditor = mPref.edit()
    }

    val authToken: String?
        get() = mPref.getString(PARAM_AUTHENTICATION_TOKEN,"")

    fun saveAuthToken(token: String){
        mEditor.putString(PARAM_AUTHENTICATION_TOKEN,token).commit()
    }
    fun deleteAuthToken(){
        mEditor.remove(PARAM_AUTHENTICATION_TOKEN).commit()
    }

    fun logoutUser(){
        deleteAuthToken()

    }
    fun saveCurrencyId(currencyId : Int){
        mEditor.putInt(PARAM_CURRENCY, currencyId).commit()
    }
    
    fun getCurrencyId() : Int {
        return mPref.getInt(PARAM_CURRENCY, 0)
    }

    companion object {
        private val PREF_NAME = "Traticket"
        private val PARAM_AUTHENTICATION_TOKEN = "authentication_token"
        private val PARAM_HASH_TICKETS = "hash_tickets"
        private val PARAM_HASH_LOCATIONS = "hash_locations"
        private val PARAM_HASH_FAVOURITES = "hash_favourites"
        private val PARAM_CURRENCY = "currency"
    }
}