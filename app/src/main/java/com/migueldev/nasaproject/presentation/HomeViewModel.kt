package com.migueldev.nasaproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.domain.usecase.GetFeaturedItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedItemUseCase: GetFeaturedItemUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun updateStartDate(date: LocalDate?) {
        _state.value = _state.value.copy(
            dateRange = _state.value.dateRange.copy(startDate = date)
        )
    }
    
    fun updateEndDate(date: LocalDate?) {
        _state.value = _state.value.copy(
            dateRange = _state.value.dateRange.copy(endDate = date)
        )
    }
    
    fun loadItemsByDateRange() {
        val currentRange = _state.value.dateRange
        if (!currentRange.isValid) return
        
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    isLoading = true,
                    errorMessage = null
                )
                
                val items = getFeaturedItemUseCase.getItemsByDateRange(
                    currentRange.startDate!!,
                    currentRange.endDate!!
                )
                
                _state.value = _state.value.copy(
                    isLoading = false,
                    items = items
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error loading data"
                )
            }
        }
    }
    
    // Mantener la función original para compatibilidad
    fun loadFeaturedItem() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true, errorMessage = null)
                val item = getFeaturedItemUseCase()
                _state.value = _state.value.copy(
                    isLoading = false,
                    items = listOf(item)
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

