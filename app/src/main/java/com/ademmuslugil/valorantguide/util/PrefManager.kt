package com.ademmuslugil.valorantguide.util

import android.content.Context
import androidx.preference.PreferenceManager
import javax.inject.Inject

class PrefManager @Inject constructor(context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)


    private companion object {
        const val APP_LANGUAGE = "app_language"
    }

    var appLanguage: String
        get() = preferences.getString(APP_LANGUAGE,"English") ?: "English"
        set(value) {preferences.edit().putString(APP_LANGUAGE, value).apply()}
}