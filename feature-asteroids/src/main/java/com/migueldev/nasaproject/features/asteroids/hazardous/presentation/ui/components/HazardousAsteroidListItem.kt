package com.migueldev.nasaproject.features.asteroids.hazardous.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid

@Composable
fun HazardousAsteroidListItem(
    asteroid: Asteroid,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = asteroid.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                
                if (asteroid.isHazardous) {
                    Text(
                        text = "⚠️ HAZARDOUS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE31E24),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Diameter",
                        fontSize = 12.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        text = "${String.format("%.2f", asteroid.diameter)} km",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Distance",
                        fontSize = 12.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        text = "${String.format("%.4f", asteroid.distance)} AU",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFFFAA00)
                    )
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Velocity",
                        fontSize = 12.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        text = "${String.format("%.2f", asteroid.velocity)} km/s",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Approach Date",
                        fontSize = 12.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        text = asteroid.approachDate,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}


