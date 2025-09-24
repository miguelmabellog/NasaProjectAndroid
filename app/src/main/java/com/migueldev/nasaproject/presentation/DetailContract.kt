package com.migueldev.nasaproject.presentation

import com.migueldev.nasaproject.domain.model.NasaItem

sealed class DetailState {
    object Loading : DetailState()
    data class Loaded(val item: NasaItem) : DetailState()
    data class Error(val message: String) : DetailState()
}
