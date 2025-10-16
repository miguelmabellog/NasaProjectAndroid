package com.migueldev.nasaproject.features.asteroids.hazardous.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.migueldev.nasaproject.features.asteroids.hazardous.presentation.state.HazardousAsteroidsUiState
import com.migueldev.nasaproject.features.asteroids.hazardous.presentation.ui.components.HazardousAsteroidListItem
import com.migueldev.nasaproject.features.asteroids.hazardous.presentation.viewmodel.HazardousAsteroidsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HazardousAsteroidsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: HazardousAsteroidsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Hazardous Asteroids",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A1A),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is HazardousAsteroidsUiState.Loading -> {
                LoadingState(modifier = modifier.padding(paddingValues))
            }
            
            is HazardousAsteroidsUiState.Success -> {
                val asteroids = (uiState as HazardousAsteroidsUiState.Success).asteroids
                
                if (asteroids.isEmpty()) {
                    EmptyState(
                        modifier = modifier.padding(paddingValues),
                        onRetry = { viewModel.loadHazardousAsteroids() }
                    )
                } else {
                    SuccessState(
                        asteroids = asteroids,
                        onAsteroidClick = onNavigateToDetail,
                        modifier = modifier.padding(paddingValues)
                    )
                }
            }
            
            is HazardousAsteroidsUiState.Error -> {
                ErrorState(
                    message = (uiState as HazardousAsteroidsUiState.Error).message,
                    onRetry = { viewModel.loadHazardousAsteroids() },
                    modifier = modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(color = Color(0xFFE31E24))
            Text(
                text = "Loading hazardous asteroids...",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun SuccessState(
    asteroids: List<com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid>,
    onAsteroidClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "⚠️ ${asteroids.size} potentially hazardous asteroids approaching in the next 7 days",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFFFAA00),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
        
        items(asteroids) { asteroid ->
            HazardousAsteroidListItem(
                asteroid = asteroid,
                onClick = { onAsteroidClick(asteroid.id) }
            )
        }
    }
}

@Composable
private fun EmptyState(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "✅",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "No Hazardous Asteroids",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Good news! No potentially hazardous asteroids detected in the next 7 days.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFCCCCCC),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                )
            ) {
                Text("Refresh")
            }
        }
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "❌",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "Error",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFCCCCCC),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                )
            ) {
                Text("Retry")
            }
        }
    }
}


