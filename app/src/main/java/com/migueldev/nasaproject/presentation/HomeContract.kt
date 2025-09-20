package com.migueldev.nasaproject.presentation

import com.migueldev.nasaproject.domain.model.NasaItem

sealed class HomeState {
    object Loading : HomeState()
    data class Loaded(val item: NasaItem) : HomeState()
    data class Error(val message: String) : HomeState()
}



