package com.migueldev.nasaproject.features.asteroids.common.domain.repository

import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid

interface AsteroidsRepository {
    suspend fun getAsteroids(
        startDate: String,
        endDate: String
    ): Result<List<Asteroid>>
}

