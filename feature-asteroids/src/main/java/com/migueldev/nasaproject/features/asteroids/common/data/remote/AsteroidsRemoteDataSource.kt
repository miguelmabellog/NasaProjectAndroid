package com.migueldev.nasaproject.features.asteroids.common.data.remote

import com.google.gson.Gson
import com.migueldev.nasaproject.core.network.api.NasaApiService
import com.migueldev.nasaproject.features.asteroids.common.data.remote.dto.NeoFeedResponseDto
import javax.inject.Inject

class AsteroidsRemoteDataSource @Inject constructor(
    private val apiService: NasaApiService,
    private val gson: Gson
) {
    suspend fun getAsteroidsFeed(
        apiKey: String,
        startDate: String,
        endDate: String
    ): NeoFeedResponseDto {
        val jsonResponse = apiService.getAsteroidsFeed(apiKey, startDate, endDate)
        return gson.fromJson(jsonResponse, NeoFeedResponseDto::class.java)
    }
}

