package com.migueldev.nasaproject.features.asteroids.todays.domain.usecase

import com.migueldev.nasaproject.features.asteroids.common.domain.repository.AsteroidsRepository
import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid
import java.time.LocalDate
import javax.inject.Inject

class GetTodaysAsteroidsUseCase @Inject constructor(
    private val repository: AsteroidsRepository
) {
    suspend operator fun invoke(): Result<List<Asteroid>> {
        val today = LocalDate.now()
        
        return repository.getAsteroids(
            startDate = today.toString(),
            endDate = today.toString()
        ).map { asteroids ->
            asteroids.sortedBy { it.distance } // Closest first
        }
    }
}


