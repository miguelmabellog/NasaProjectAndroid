package com.migueldev.nasaproject.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.migueldev.nasaproject.domain.model.NasaItem
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoOfDayScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }
    
    val startDatePickerState = rememberDatePickerState()
    val endDatePickerState = rememberDatePickerState()
    
    // Auto-load current day image when screen opens
    LaunchedEffect(Unit) {
        viewModel.loadFeaturedItem()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Astronomy Picture of the Day",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1A1A1A))
        ) {
            // Date Range Selector Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Select Date Range",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { showStartDatePicker = true },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE31E24)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Start Date",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        
                        Button(
                            onClick = { showEndDatePicker = true },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE31E24)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "End Date",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                    
                    Button(
                        onClick = {
                            val startDate = startDatePickerState.selectedDateMillis?.let { millis ->
                                Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                            }
                            val endDate = endDatePickerState.selectedDateMillis?.let { millis ->
                                Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                            }
                            
                            if (startDate != null && endDate != null) {
                                viewModel.updateStartDate(startDate)
                                viewModel.updateEndDate(endDate)
                                viewModel.loadItemsByDateRange()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = startDatePickerState.selectedDateMillis != null && 
                                 endDatePickerState.selectedDateMillis != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Load Photos")
                    }
                }
            }
            
            // Photos List
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFE31E24))
                    }
                }
                state.hasError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.errorMessage ?: "Unknown error",
                            color = Color(0xFFFF5252),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                state.hasItems -> {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.items) { item ->
                            NasaPhotoCard(
                                item = item,
                                onClick = { onNavigateToDetail(item.id) }
                            )
                        }
                    }
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Select a date range to view photos",
                            color = Color(0xFF999999),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        
        // Date Pickers
        if (showStartDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showStartDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showStartDatePicker = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showStartDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = startDatePickerState)
            }
        }
        
        if (showEndDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showEndDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showEndDatePicker = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEndDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = endDatePickerState)
            }
        }
    }
}

@Composable
private fun NasaPhotoCard(
    item: NasaItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Image
            if (item.imageUrl != null && item.isAvailable) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = rememberAsyncImagePainter(
                        model = "https://via.placeholder.com/400x200/333333/666666?text=Loading..."
                    ),
                    error = rememberAsyncImagePainter(
                        model = "https://via.placeholder.com/400x200/333333/ff6666?text=Error"
                    )
                )
            } else if (!item.isAvailable) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFF333333)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Image not available",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFFF5252),
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            // Content
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Date
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFFE31E24),
                    fontWeight = FontWeight.Medium
                )
                
                // Title
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                // Description preview
                Text(
                    text = if (item.isAvailable) {
                        item.description.take(120) + if (item.description.length > 120) "..." else ""
                    } else {
                        "No description available for this date"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFCCCCCC),
                    lineHeight = 18.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
