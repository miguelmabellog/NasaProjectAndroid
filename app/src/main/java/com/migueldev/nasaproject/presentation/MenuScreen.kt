package com.migueldev.nasaproject.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MenuScreen(
    onNavigateToPhotoOfDay: () -> Unit,
    onNavigateToAsteroids: () -> Unit,
    onNavigateToPreferences: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val userName by viewModel.userName.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "NASA Project",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Text(
                text = "Explora el universo",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFCCCCCC),
                textAlign = TextAlign.Center
            )
            
            // Mostrar saludo personalizado si hay nombre guardado
            if (userName.isNotEmpty()) {
                Text(
                    text = "¡Hola, $userName! 👋",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFE31E24),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            Button(
                onClick = onNavigateToPhotoOfDay,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Foto del Día",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Button(
                onClick = onNavigateToAsteroids,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Asteroids",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Button(
                onClick = onNavigateToPreferences,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A4A4A)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "⚙️ Preferencias",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
