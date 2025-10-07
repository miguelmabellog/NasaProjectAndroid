package com.migueldev.nasaproject.features.photoofday.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.features.photoofday.domain.usecase.GetFeaturedItemUseCase
import com.migueldev.nasaproject.features.photoofday.domain.usecase.GetItemsByDateRangeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * ViewModel for Photo of Day home screen
 * This handles the business logic and state management for the Photo of Day screen
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedItemUseCase: GetFeaturedItemUseCase,
    private val getItemsByDateRangeUseCase: GetItemsByDateRangeUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()
    
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    
    init {
        loadFeaturedItem()
    }
    
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadFeaturedItem -> {
                loadFeaturedItem()
            }
            is HomeEvent.LoadItemsByDateRange -> {
                loadItemsByDateRange(event.startDate, event.endDate)
            }
            is HomeEvent.OnItemClick -> {
                // Navigation will be handled by the UI layer
            }
            is HomeEvent.ClearError -> {
                _state.value = _state.value.copy(error = null)
            }
        }
    }
    
    private fun loadFeaturedItem() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            
            getFeaturedItemUseCase()
                .onSuccess { item ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        featuredItem = item,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Error loading featured item"
                    )
                }
        }
    }
    
    private fun loadItemsByDateRange(startDate: String, endDate: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            
            try {
                val start = LocalDate.parse(startDate, dateFormatter)
                val end = LocalDate.parse(endDate, dateFormatter)
                
                getItemsByDateRangeUseCase(start, end)
                    .onSuccess { items ->
                        _state.value = _state.value.copy(
                            isLoading = false,
                            itemsByRange = items,
                            startDate = startDate,
                            endDate = endDate,
                            error = null
                        )
                    }
                    .onFailure { exception ->
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Error loading items by date range"
                        )
                    }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Invalid date format"
                )
            }
        }
    }
}
