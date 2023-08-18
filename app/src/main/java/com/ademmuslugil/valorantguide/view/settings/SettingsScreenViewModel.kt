package com.ademmuslugil.valorantguide.view.settings

import android.app.Activity
import android.os.Build
import androidx.lifecycle.ViewModel
import com.ademmuslugil.valorantguide.util.PrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor (): ViewModel() {

    @Inject
    lateinit var prefManager: PrefManager

    private var appLanguage: String
        get() = prefManager.appLanguage
        set(value) {
            prefManager.appLanguage = value
        }

    fun getLanguage(): String {
       return appLanguage
    }

    fun setLanguage(value: String) {
        appLanguage = value
    }



    fun setAppLocale(activity: Activity, viewModel: SettingsScreenViewModel) {
        val languageCode = checkLanguage(viewModel)
        val resources = activity.resources
        val configuration = resources.configuration

        val locale = Locale(languageCode)
        Locale.setDefault(locale)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)
            configuration.setLayoutDirection(locale)

        } else {
            @Suppress("DEPRECATION")
            configuration.locale = locale
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        activity.recreate()
    }


    private fun checkLanguage(viewModel: SettingsScreenViewModel): String {
        return if (viewModel.prefManager.appLanguage == "English")
            "en"
        else
            "tr"
    }



}