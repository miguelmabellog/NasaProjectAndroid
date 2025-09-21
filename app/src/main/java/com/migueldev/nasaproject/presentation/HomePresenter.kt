package com.migueldev.nasaproject.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.migueldev.nasaproject.domain.usecase.GetFeaturedItemUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val getFeaturedItemUseCase: GetFeaturedItemUseCase
) {
    var state: HomeState by mutableStateOf(HomeState())
        private set

    private var loadJob: Job? = null

    fun loadFeaturedItem() {
        loadJob?.cancel()
        loadJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                state = state.copy(isLoading = true, errorMessage = null)
                val item = getFeaturedItemUseCase()
                state = state.copy(
                    isLoading = false,
                    items = listOf(item)
                )
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}