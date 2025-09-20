package com.migueldev.nasaproject.data.source.remote

import com.migueldev.nasaproject.data.source.remote.dto.NasaResponseDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaRemoteDataSource @Inject constructor(
    private val apiService: NasaApiService
) {
    suspend fun getAstronomyPictureOfTheDay(apiKey: String, date: String? = null): NasaResponseDto {
        return apiService.getAstronomyPictureOfTheDay(apiKey, date)
    }
}

