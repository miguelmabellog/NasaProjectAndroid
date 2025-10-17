package com.migueldev.nasaproject.features.photoofday.data.source.impl

import com.migueldev.nasaproject.core.common.di.NasaApiKey
import com.migueldev.nasaproject.core.network.api.NasaApiService
import com.migueldev.nasaproject.core.network.dto.NasaResponseDto
import com.migueldev.nasaproject.features.photoofday.data.source.PhotoOfDayRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote data source for Photo of Day feature
 * This handles all network calls for the Photo of Day data
 */
@Singleton
class PhotoOfDayRemoteDataSourceImpl @Inject constructor(
  private val apiService: NasaApiService,
  @NasaApiKey private val apiKey: String
) : PhotoOfDayRemoteDataSource {

  /**
   * Get today's featured item
   */
  override suspend fun getFeaturedItem(): NasaResponseDto {
    return apiService.getAstronomyPictureOfTheDay(apiKey)
  }

  /**
   * Get item by specific date
   */
  override suspend fun getItemByDate(date: String): NasaResponseDto {
    return apiService.getAstronomyPictureOfTheDay(apiKey, date)
  }

  /**
   * Get items within date range
   */
  override suspend fun getItemsByDateRange(
    startDate: String,
    endDate: String
  ): List<NasaResponseDto> {
    return apiService.getAstronomyPicturesInRange(apiKey, startDate, endDate)
  }
}