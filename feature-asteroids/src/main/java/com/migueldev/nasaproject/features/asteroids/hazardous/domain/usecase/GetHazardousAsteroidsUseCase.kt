package com.migueldev.nasaproject.features.asteroids.hazardous.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.migueldev.nasaproject.features.asteroids.common.domain.repository.AsteroidsRepository
import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetHazardousAsteroidsUseCase @Inject constructor(
    private val repository: AsteroidsRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Result<List<Asteroid>> {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        
        val today = dateFormat.format(calendar.time)
        
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val endDate = dateFormat.format(calendar.time)
        
        return repository.getAsteroids(
            startDate = today,
            endDate = endDate
        ).map { asteroids ->
            asteroids
                .filter { it.isHazardous }
                .sortedBy { it.distance } // Closest first
        }
    }
}

