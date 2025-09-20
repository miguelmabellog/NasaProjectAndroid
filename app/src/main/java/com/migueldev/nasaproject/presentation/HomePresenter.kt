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
    var state: HomeState by mutableStateOf<HomeState>(HomeState.Loading)
        private set

    private var loadJob: Job? = null

    fun loadFeaturedItem() {
        loadJob?.cancel()
        loadJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                state = HomeState.Loading
                val item = getFeaturedItemUseCase()
                state = HomeState.Loaded(item)
            } catch (e: Exception) {
                state = HomeState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}