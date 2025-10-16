package com.migueldev.nasaproject.core.common.preferences

import android.content.Context
import com.migueldev.nasaproject.core.common.di.ApplicationContext
import dagger.hilt.android.qualifiers.ApplicationContext as HiltApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(
    @HiltApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    
    var userName: String
        get() = prefs.getString(KEY_USER_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_USER_NAME, value).apply()
    
    var userLanguage: String
        get() = prefs.getString(KEY_USER_LANGUAGE, LANGUAGE_SPANISH) ?: LANGUAGE_SPANISH
        set(value) = prefs.edit().putString(KEY_USER_LANGUAGE, value).apply()
    
    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_LANGUAGE = "user_language"
        
        const val LANGUAGE_SPANISH = "es"
        const val LANGUAGE_ENGLISH = "en"
    }
}
