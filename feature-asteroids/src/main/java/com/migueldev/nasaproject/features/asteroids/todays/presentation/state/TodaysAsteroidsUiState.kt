package com.migueldev.nasaproject.features.asteroids.todays.presentation.state

import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid

sealed interface TodaysAsteroidsUiState {
    object Loading : TodaysAsteroidsUiState
    data class Success(val asteroids: List<Asteroid>) : TodaysAsteroidsUiState
    data class Error(val message: String) : TodaysAsteroidsUiState
}

