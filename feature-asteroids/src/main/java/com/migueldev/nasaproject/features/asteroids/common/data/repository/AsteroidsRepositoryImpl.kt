package com.migueldev.nasaproject.features.asteroids.common.data.repository

import com.migueldev.nasaproject.core.common.di.NasaApiKey
import com.migueldev.nasaproject.features.asteroids.common.data.mapper.AsteroidMapper
import com.migueldev.nasaproject.features.asteroids.common.data.remote.AsteroidsRemoteDataSource
import com.migueldev.nasaproject.features.asteroids.common.domain.repository.AsteroidsRepository
import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsteroidsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AsteroidsRemoteDataSource,
    @NasaApiKey private val apiKey: String
) : AsteroidsRepository {
    
    override suspend fun getAsteroids(
        startDate: String,
        endDate: String
    ): Result<List<Asteroid>> {
        return try {
            val response = remoteDataSource.getAsteroidsFeed(apiKey, startDate, endDate)
            val asteroids = AsteroidMapper.toDomainList(response)
            Result.success(asteroids)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

