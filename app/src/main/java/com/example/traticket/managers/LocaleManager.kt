package com.example.traticket.managers

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.preference.PreferenceManager
import java.util.*

object LocaleManager {

    private val lOCALE_FRENCH = "fr"
    private val LOCALE_ENGLISH = "en"

    private val LANGUAGE_KEY = "language_key"


    fun setLocale(mContext: Context): Context{
        return updatResources(mContext,getLanguagePref(mContext)!!)
    }

    fun setNewLocale(mContext: Context,mLocaleKey: String): Context{
        setLanguagePref(mContext,mLocaleKey)
        return updatResources(mContext,mLocaleKey)
    }

    fun getLanguagePref(mContext: Context): String? {
        val mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        return mPreferences.getString(LANGUAGE_KEY, LOCALE_ENGLISH)
    }

    fun setLanguagePref(mContext: Context, localekey: String){
        val mPreferenceManager = PreferenceManager.getDefaultSharedPreferences(mContext)
        mPreferenceManager.edit().putString(LANGUAGE_KEY,localekey).apply()
    }

    fun updatResources(context: Context, language: String): Context{
        var context = context
        val locale: Locale = if(language == lOCALE_FRENCH){
            Locale(language,language.reversed().toUpperCase())
        }else{
            Locale(language,language.toUpperCase())
        }
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        context =context.createConfigurationContext(config)

        return context
    }

    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if(Build.VERSION.SDK_INT >= 24 ) config.locales.get(0) else config.locale
    }
}