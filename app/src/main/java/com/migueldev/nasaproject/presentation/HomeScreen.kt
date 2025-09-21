package com.migueldev.nasaproject.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.migueldev.nasaproject.presentation.components.DateRangeSelector
import com.migueldev.nasaproject.presentation.components.NasaItemCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.loadFeaturedItem()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // DatePickers inline
        DateRangeSelector(
            startDate = state.dateRange.startDate,
            endDate = state.dateRange.endDate,
            onStartDateChange = viewModel::updateStartDate,
            onEndDateChange = viewModel::updateEndDate,
            onSearch = viewModel::loadItemsByDateRange
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Contenido
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.hasError -> {
                Text(
                    text = state.errorMessage ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
            state.hasItems -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.items) { item ->
                        NasaItemCard(item = item)
                    }
                }
            }
            else -> {
                Text(
                    text = "Selecciona un rango de fechas para buscar imágenes APOD",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


