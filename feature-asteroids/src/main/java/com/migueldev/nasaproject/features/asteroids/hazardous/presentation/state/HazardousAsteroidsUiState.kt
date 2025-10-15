package com.migueldev.nasaproject.features.asteroids.hazardous.presentation.state

import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid

sealed interface HazardousAsteroidsUiState {
    object Loading : HazardousAsteroidsUiState
    data class Success(val asteroids: List<Asteroid>) : HazardousAsteroidsUiState
    data class Error(val message: String) : HazardousAsteroidsUiState
}

