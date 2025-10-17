package com.migueldev.nasaproject.features.asteroids.todays.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.features.asteroids.todays.domain.usecase.GetTodaysAsteroidsUseCase
import com.migueldev.nasaproject.features.asteroids.todays.presentation.state.TodaysAsteroidsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodaysAsteroidsViewModel @Inject constructor(
    private val getTodaysAsteroidsUseCase: GetTodaysAsteroidsUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<TodaysAsteroidsUiState>(TodaysAsteroidsUiState.Loading)
    val uiState: StateFlow<TodaysAsteroidsUiState> = _uiState.asStateFlow()
    
    init {
        loadTodaysAsteroids()
    }
    
    fun loadTodaysAsteroids() {
        viewModelScope.launch {
            _uiState.value = TodaysAsteroidsUiState.Loading
            
            getTodaysAsteroidsUseCase()
                .onSuccess { asteroids ->
                    _uiState.value = TodaysAsteroidsUiState.Success(asteroids)
                }
                .onFailure { error ->
                    _uiState.value = TodaysAsteroidsUiState.Error(
                        error.message ?: "Unknown error occurred"
                    )
                }
        }
    }
}



