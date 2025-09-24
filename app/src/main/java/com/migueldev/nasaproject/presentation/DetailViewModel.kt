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
class DetailViewModel @Inject constructor(
    private val getFeaturedItemUseCase: GetFeaturedItemUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun loadItem(itemId: String) {
        viewModelScope.launch {
            try {
                _state.value = DetailState.Loading
                
                // Por ahora, cargamos el item destacado
                // En una implementación más completa, tendríamos una función específica para obtener por ID
                val item = getFeaturedItemUseCase()
                
                // Verificar si el item coincide con el ID solicitado
                if (item.id == itemId) {
                    _state.value = DetailState.Loaded(item)
                } else {
                    _state.value = DetailState.Error("Item no encontrado")
                }
            } catch (e: Exception) {
                _state.value = DetailState.Error(e.message ?: "Error al cargar el item")
            }
        }
    }
}
