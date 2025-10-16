package com.migueldev.nasaproject.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.core.common.preferences.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(PreferencesUiState())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()
    
    init {
        loadPreferences()
    }
    
    private fun loadPreferences() {
        viewModelScope.launch {
            val currentUserName = appPreferences.userName
            val currentLanguage = when (appPreferences.userLanguage) {
                AppPreferences.LANGUAGE_SPANISH -> "Español"
                AppPreferences.LANGUAGE_ENGLISH -> "English"
                else -> "Español"
            }
            
            _uiState.value = _uiState.value.copy(
                userName = currentUserName,
                selectedLanguage = currentLanguage
            )
        }
    }
    
    fun updateUserName(name: String) {
        _uiState.value = _uiState.value.copy(userName = name)
    }
    
    fun updateLanguage(language: String) {
        _uiState.value = _uiState.value.copy(selectedLanguage = language)
    }
    
    fun savePreferences() {
        viewModelScope.launch {
            val state = _uiState.value
            
            // Guardar nombre
            appPreferences.userName = state.userName
            
            // Guardar idioma
            val languageCode = when (state.selectedLanguage) {
                "English" -> AppPreferences.LANGUAGE_ENGLISH
                else -> AppPreferences.LANGUAGE_SPANISH
            }
            appPreferences.userLanguage = languageCode
            
            _uiState.value = _uiState.value.copy(isSaved = true)
        }
    }
}

data class PreferencesUiState(
    val userName: String = "",
    val selectedLanguage: String = "Español",
    val isSaved: Boolean = false
)
