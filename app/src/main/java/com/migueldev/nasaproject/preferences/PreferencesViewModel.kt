package com.migueldev.nasaproject.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.core.common.preferences.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
  private val dataStoreManager: DataStoreManager
) : ViewModel() {

  private val _uiState = MutableStateFlow(PreferencesUiState())
  val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()

  init {
    loadPreferences()
  }

  private fun loadPreferences() {
    viewModelScope.launch {
      combine(
        dataStoreManager.userName,
        dataStoreManager.language,
      ) { userName, language ->

        _uiState.value = _uiState.value.copy(
          userName = userName,
          selectedLanguage = language
        )
      }.collect()
    }
  }

  fun updateUserName(name: String) {
    _uiState.value = _uiState.value.copy(userName = name)
  }

  fun updateLanguage(language: String) {
    _uiState.value = _uiState.value.copy(selectedLanguage = language)
  }

  fun savePreferences(onFinish: () -> Unit) {
    viewModelScope.launch {
      val state = _uiState.value

      dataStoreManager.saveUserName(state.userName)

      dataStoreManager.saveLanguage(state.selectedLanguage)

      onFinish()
    }
  }
}

data class PreferencesUiState(
  val userName: String = "",
  val selectedLanguage: String = "en",
)
