package com.migueldev.nasaproject.features.photoofday.presentation.viewmodel

import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem

/**
 * Contract for Home screen state and events
 * This defines the UI state and user interactions for the Photo of Day screen
 */
data class HomeState(
    val isLoading: Boolean = false,
    val featuredItem: NasaItem? = null,
    val itemsByRange: List<NasaItem> = emptyList(),
    val error: String? = null,
    val startDate: String? = null,
    val endDate: String? = null
)

sealed class HomeEvent {
    object LoadFeaturedItem : HomeEvent()
    data class LoadItemsByDateRange(val startDate: String, val endDate: String) : HomeEvent()
    data class OnItemClick(val itemId: String) : HomeEvent()
    object ClearError : HomeEvent()
}
