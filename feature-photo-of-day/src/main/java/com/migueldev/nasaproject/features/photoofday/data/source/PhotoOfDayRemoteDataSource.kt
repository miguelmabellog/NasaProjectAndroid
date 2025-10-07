package com.migueldev.nasaproject.features.photoofday.data.source

import com.migueldev.nasaproject.core.common.di.NasaApiKey
import com.migueldev.nasaproject.core.network.api.NasaApiService
import com.migueldev.nasaproject.core.network.dto.NasaResponseDto
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote data source for Photo of Day feature
 * This handles all network calls for the Photo of Day data
 */
@Singleton
class PhotoOfDayRemoteDataSource @Inject constructor(
    private val apiService: NasaApiService,
    @NasaApiKey private val apiKey: String
) {
    
    /**
     * Get today's featured item
     */
    suspend fun getFeaturedItem(): NasaResponseDto {
        return apiService.getAstronomyPictureOfTheDay(apiKey)
    }
    
    /**
     * Get item by specific date
     */
    suspend fun getItemByDate(date: String): NasaResponseDto {
        return apiService.getAstronomyPictureOfTheDay(apiKey, date)
    }
    
    /**
     * Get items within date range
     */
    suspend fun getItemsByDateRange(
        startDate: String,
        endDate: String
    ): List<NasaResponseDto> {
        return apiService.getAstronomyPicturesInRange(apiKey, startDate, endDate)
    }
}
