package com.migueldev.nasaproject.features.photoofday.data.repository

import com.migueldev.nasaproject.features.photoofday.data.mapper.NasaMapper
import com.migueldev.nasaproject.features.photoofday.data.source.PhotoOfDayRemoteDataSource
import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem
import com.migueldev.nasaproject.features.photoofday.domain.repository.PhotoOfDayRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of PhotoOfDayRepository
 * This handles the data access logic and coordinates between different data sources
 */
@Singleton
class PhotoOfDayRepositoryImpl @Inject constructor(
  private val remoteDataSource: PhotoOfDayRemoteDataSource
) : PhotoOfDayRepository {

  private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

  override suspend fun getFeaturedItem(): Result<NasaItem> {
    return try {
      val dto = remoteDataSource.getFeaturedItem()
      val domainModel = NasaMapper.toDomainModel(dto)
      Result.success(domainModel)
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  override suspend fun getItemByDate(date: LocalDate): Result<NasaItem> {
    return try {
      val dateString = date.format(dateFormatter)
      val dto = remoteDataSource.getItemByDate(dateString)
      val domainModel = NasaMapper.toDomainModel(dto)
      Result.success(domainModel)
    } catch (e: Exception) {
      Result.failure(e)
    }
  }

  override suspend fun getItemsByDateRange(
    startDate: LocalDate,
    endDate: LocalDate
  ): Result<List<NasaItem>> {
    return try {
      val startDateString = startDate.format(dateFormatter)
      val endDateString = endDate.format(dateFormatter)
      val dtoList = remoteDataSource.getItemsByDateRange(startDateString, endDateString)
      val domainModels = NasaMapper.toDomainModelList(dtoList)
      Result.success(domainModels)
    } catch (e: Exception) {
      Result.failure(e)
    }
  }
}
