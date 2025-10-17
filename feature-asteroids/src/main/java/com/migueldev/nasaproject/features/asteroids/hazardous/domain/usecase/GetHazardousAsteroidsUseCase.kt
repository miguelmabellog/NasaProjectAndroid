package com.migueldev.nasaproject.features.asteroids.hazardous.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.migueldev.nasaproject.features.asteroids.common.domain.repository.AsteroidsRepository
import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid
import java.time.LocalDate
import javax.inject.Inject

class GetHazardousAsteroidsUseCase @Inject constructor(
    private val repository: AsteroidsRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Result<List<Asteroid>> {
        val today = LocalDate.now()
        val endDate = today.plusDays(7) // Next 7 days
        
        return repository.getAsteroids(
            startDate = today.toString(),
            endDate = endDate.toString()
        ).map { asteroids ->
            asteroids
                .filter { it.isHazardous }
                .sortedBy { it.distance } // Closest first
        }
    }
}

