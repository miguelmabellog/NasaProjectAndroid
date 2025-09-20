package com.migueldev.nasaproject.data.repository

import com.migueldev.nasaproject.data.source.remote.NasaRemoteDataSource
import com.migueldev.nasaproject.di.NasaApiKey
import com.migueldev.nasaproject.domain.model.NasaItem
import com.migueldev.nasaproject.domain.repository.NasaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaRepositoryImpl @Inject constructor(
    private val remoteDataSource: NasaRemoteDataSource,
    @NasaApiKey private val apiKey: String
) : NasaRepository {
    
    override suspend fun getFeaturedItem(): NasaItem {
        val response = remoteDataSource.getAstronomyPictureOfTheDay(apiKey)
        
        return NasaItem(
            id = response.date,
            title = response.title,
            description = response.explanation
        )
    }
}