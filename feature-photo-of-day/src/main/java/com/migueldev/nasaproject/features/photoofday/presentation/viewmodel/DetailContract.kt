package com.migueldev.nasaproject.features.photoofday.presentation.viewmodel

import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem

/**
 * Contract for Detail screen state
 * This defines the UI state for the Photo of Day detail screen
 */
sealed class DetailState {
    object Loading : DetailState()
    data class Success(val item: NasaItem) : DetailState()
    data class Error(val message: String) : DetailState()
}
