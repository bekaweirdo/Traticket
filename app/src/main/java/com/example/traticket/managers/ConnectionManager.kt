package com.example.traticket.managers

import android.content.Context
import androidx.preference.PreferenceManager

object ConnectionManager {
    private const val CONNECTION_KEY = "last_connection_state"

    fun setState(context: Context, lastState: Boolean){
        val mPreferences =  PreferenceManager.getDefaultSharedPreferences(context)
        mPreferences.edit().putBoolean(CONNECTION_KEY,lastState).commit()
    }

    fun getLastSavedState(context: Context): Boolean {
        val mPreferences =  PreferenceManager.getDefaultSharedPreferences(context)
        return mPreferences.getBoolean(CONNECTION_KEY, false)
    }

    fun containsState(context: Context): Boolean {
        val mPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return mPreferences.contains(CONNECTION_KEY)
    }

    fun deleteState(context: Context){
        val mPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        mPreferences.edit().remove(CONNECTION_KEY).commit()
    }
}