package com.migueldev.nasaproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.domain.usecase.GetFeaturedItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedItemUseCase: GetFeaturedItemUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun loadFeaturedItem() {
        viewModelScope.launch {
            try {
                _state.value = HomeState.Loading
                val item = getFeaturedItemUseCase()
                _state.value = HomeState.Loaded(item)
            } catch (e: Exception) {
                _state.value = HomeState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}

