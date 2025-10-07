package com.migueldev.nasaproject.features.photoofday.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.features.photoofday.domain.usecase.GetItemByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * ViewModel for Photo of Day detail screen
 * This handles the business logic and state management for the detail screen
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getItemByDateUseCase: GetItemByDateUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state.asStateFlow()
    
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    
    fun loadItemByDate(date: String) {
        viewModelScope.launch {
            _state.value = DetailState.Loading
            
            try {
                val localDate = LocalDate.parse(date, dateFormatter)
                
                getItemByDateUseCase(localDate)
                    .onSuccess { item ->
                        _state.value = DetailState.Success(item)
                    }
                    .onFailure { exception ->
                        _state.value = DetailState.Error(
                            exception.message ?: "Error loading item"
                        )
                    }
            } catch (e: Exception) {
                _state.value = DetailState.Error("Invalid date format")
            }
        }
    }
}
