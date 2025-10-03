package com.migueldev.nasaproject.data.source.remote

import com.migueldev.nasaproject.core.network.api.NasaApiService
import com.migueldev.nasaproject.core.network.dto.NasaResponseDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaRemoteDataSource @Inject constructor(
    private val apiService: NasaApiService
) {
    suspend fun getAstronomyPictureOfTheDay(apiKey: String, date: String? = null): NasaResponseDto {
        return apiService.getAstronomyPictureOfTheDay(apiKey, date)
    }
    
    suspend fun getAstronomyPicturesInRange(apiKey: String, startDate: String, endDate: String? = null): List<NasaResponseDto> {
        return apiService.getAstronomyPicturesInRange(apiKey, startDate, endDate)
    }
}

