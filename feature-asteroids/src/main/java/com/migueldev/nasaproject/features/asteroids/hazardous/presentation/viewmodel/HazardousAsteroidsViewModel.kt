package com.migueldev.nasaproject.features.asteroids.hazardous.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.features.asteroids.hazardous.domain.usecase.GetHazardousAsteroidsUseCase
import com.migueldev.nasaproject.features.asteroids.hazardous.presentation.state.HazardousAsteroidsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HazardousAsteroidsViewModel @Inject constructor(
    private val getHazardousAsteroidsUseCase: GetHazardousAsteroidsUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<HazardousAsteroidsUiState>(HazardousAsteroidsUiState.Loading)
    val uiState: StateFlow<HazardousAsteroidsUiState> = _uiState.asStateFlow()
    
    init {
        loadHazardousAsteroids()
    }
    
    fun loadHazardousAsteroids() {
        viewModelScope.launch {
            _uiState.value = HazardousAsteroidsUiState.Loading
            
            getHazardousAsteroidsUseCase()
                .onSuccess { asteroids ->
                    _uiState.value = HazardousAsteroidsUiState.Success(asteroids)
                }
                .onFailure { error ->
                    _uiState.value = HazardousAsteroidsUiState.Error(
                        error.message ?: "Unknown error occurred"
                    )
                }
        }
    }
}

