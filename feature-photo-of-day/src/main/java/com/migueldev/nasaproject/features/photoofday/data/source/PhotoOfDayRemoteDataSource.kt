package com.migueldev.nasaproject.features.photoofday.data.source

import com.migueldev.nasaproject.core.network.dto.NasaResponseDto

/**
 * Created by gesoft
 */
interface PhotoOfDayRemoteDataSource {
  suspend fun getFeaturedItem(): NasaResponseDto

  suspend fun getItemByDate(date: String): NasaResponseDto

  suspend fun getItemsByDateRange(
    startDate: String,
    endDate: String
  ): List<NasaResponseDto>
}