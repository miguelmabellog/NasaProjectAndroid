package com.migueldev.nasaproject.core.common.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext as HiltApplicationContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class DataStoreManager @Inject constructor(@HiltApplicationContext private val context: Context) {

  companion object {
    val USER_NAME_KEY = stringPreferencesKey("user_name")
    val LANGUAGE_KEY = stringPreferencesKey("language")
  }

  suspend fun saveUserName(name: String) {
    context.dataStore.edit { preferences ->
      preferences[USER_NAME_KEY] = name
    }
  }

  suspend fun saveLanguage(language: String) {
    context.dataStore.edit { preferences ->
      preferences[LANGUAGE_KEY] = language
    }
  }

  val userName: Flow<String> = context.dataStore.data
    .map { preferences ->
      preferences[USER_NAME_KEY] ?: "No name set"
    }

  val language: Flow<String> = context.dataStore.data
    .map { preferences ->
      preferences[LANGUAGE_KEY] ?: "Language not set"
    }
}