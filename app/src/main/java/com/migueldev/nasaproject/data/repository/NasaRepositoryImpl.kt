package com.migueldev.nasaproject.data.repository

import com.migueldev.nasaproject.data.mapper.NasaMapper
import com.migueldev.nasaproject.data.source.remote.NasaRemoteDataSource
import com.migueldev.nasaproject.core.common.di.NasaApiKey
import com.migueldev.nasaproject.domain.model.NasaItem
import com.migueldev.nasaproject.domain.repository.NasaRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaRepositoryImpl @Inject constructor(
    private val remoteDataSource: NasaRemoteDataSource,
    @NasaApiKey private val apiKey: String
) : NasaRepository {
    
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    
    override suspend fun getFeaturedItem(): Result<NasaItem> {
        return try {
            val response = remoteDataSource.getAstronomyPictureOfTheDay(apiKey)
            val domainModel = NasaMapper.toDomainModel(response)
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getItemsByDateRange(startDate: LocalDate, endDate: LocalDate): Result<List<NasaItem>> {
        return try {
            val responses = remoteDataSource.getAstronomyPicturesInRange(
                apiKey,
                startDate.toString(),
                endDate.toString()
            )
            val domainModels = NasaMapper.toDomainModelList(responses)
            Result.success(domainModels)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getItemByDate(date: LocalDate): Result<NasaItem> {
        return try {
            val response = remoteDataSource.getAstronomyPictureOfTheDay(apiKey, date.toString())
            val domainModel = NasaMapper.toDomainModel(response)
            Result.success(domainModel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}