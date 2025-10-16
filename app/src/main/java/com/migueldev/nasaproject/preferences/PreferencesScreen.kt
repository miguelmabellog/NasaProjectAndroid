package com.migueldev.nasaproject.preferences

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PreferencesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    
    val languages = listOf("Español", "English")
    
    // Navegar de vuelta cuando se guarde
    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) {
            onNavigateBack()
        }
    }
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "⚙️ Preferencias",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Text(
                text = "Configura tu experiencia",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFCCCCCC),
                textAlign = TextAlign.Center
            )
            
            // Campo de nombre
            OutlinedTextField(
                value = uiState.userName,
                onValueChange = { viewModel.updateUserName(it) },
                label = { 
                    Text(
                        text = "Nombre",
                        color = Color.White
                    ) 
                },
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color(0xFFE31E24),
                    unfocusedBorderColor = Color(0xFF666666),
                    focusedLabelColor = Color(0xFFE31E24),
                    unfocusedLabelColor = Color(0xFFCCCCCC)
                )
            )
            
            // Dropdown de idioma
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = uiState.selectedLanguage,
                    onValueChange = {},
                    readOnly = true,
                    label = { 
                        Text(
                            text = "Idioma",
                            color = Color.White
                        ) 
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFFE31E24),
                        unfocusedBorderColor = Color(0xFF666666),
                        focusedLabelColor = Color(0xFFE31E24),
                        unfocusedLabelColor = Color(0xFFCCCCCC)
                    )
                )
                
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color(0xFF2A2A2A))
                ) {
                    languages.forEach { language ->
                        DropdownMenuItem(
                            text = { 
                                Text(
                                    text = language,
                                    color = Color.White
                                ) 
                            },
                            onClick = {
                                viewModel.updateLanguage(language)
                                expanded = false
                            }
                        )
                    }
                }
            }
            
            // Botón Guardar
            Button(
                onClick = {
                    viewModel.savePreferences()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE31E24)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Guardar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            
            // Botón Cancelar
            Button(
                onClick = onNavigateBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A4A4A)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Cancelar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
