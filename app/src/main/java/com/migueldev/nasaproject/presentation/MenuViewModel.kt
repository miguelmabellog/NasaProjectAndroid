package com.migueldev.nasaproject.presentation

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
class MenuViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : ViewModel() {
    
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()
    
    init {
        loadUserName()
    }
    
    private fun loadUserName() {
        viewModelScope.launch {
            // Cargar el nombre desde SharedPreferences
            // Esto demuestra la limitación: no se actualiza automáticamente
            val savedName = appPreferences.userName
            _userName.value = savedName
        }
    }
    
    // Método para refrescar manualmente (para demostrar la diferencia con DataStore)
    fun refreshUserName() {
        loadUserName()
    }
}
