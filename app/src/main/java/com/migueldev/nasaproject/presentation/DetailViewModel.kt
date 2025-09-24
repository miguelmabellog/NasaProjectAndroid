package com.migueldev.nasaproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.nasaproject.domain.usecase.GetItemByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getItemByDateUseCase: GetItemByDateUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state.asStateFlow()
    
    fun loadItem(itemId: String) {
        viewModelScope.launch {
            try {
                _state.value = DetailState.Loading
                val item = getItemByDateUseCase(itemId)
                _state.value = DetailState.Loaded(item)
            } catch (e: Exception) {
                _state.value = DetailState.Error(
                    e.message ?: "Error loading item"
                )
            }
        }
    }
}
