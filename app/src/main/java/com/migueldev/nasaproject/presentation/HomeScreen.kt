package com.migueldev.nasaproject.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.loadFeaturedItem()
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.hasError -> Text(text = state.errorMessage ?: "Unknown error", color = MaterialTheme.colorScheme.error)
            state.hasItems -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.items.first().title, style = MaterialTheme.typography.headlineMedium)
                Text(text = state.items.first().description, modifier = Modifier.padding(top = 12.dp))
            }
            else -> Text(text = "No data available", color = MaterialTheme.colorScheme.onSurface)
        }
    }
}


