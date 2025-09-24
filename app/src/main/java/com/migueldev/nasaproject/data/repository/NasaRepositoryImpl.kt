package com.migueldev.nasaproject.data.repository

import com.migueldev.nasaproject.data.source.remote.NasaRemoteDataSource
import com.migueldev.nasaproject.di.NasaApiKey
import com.migueldev.nasaproject.domain.model.NasaItem
import com.migueldev.nasaproject.domain.repository.NasaRepository
import java.time.LocalDate
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
            description = response.explanation,
            date = response.date,
            imageUrl = response.url,
            isAvailable = true
        )
    }
    
    override suspend fun getItemsByDateRange(startDate: LocalDate, endDate: LocalDate): List<NasaItem> {
        return try {
            // Usar la nueva función optimizada que obtiene todas las fechas en una sola llamada
            val responses = remoteDataSource.getAstronomyPicturesInRange(
                apiKey,
                startDate.toString(),
                endDate.toString()
            )
            
            // Convertir las respuestas a NasaItem
            responses.map { response ->
                NasaItem(
                    id = response.date,
                    title = response.title,
                    description = response.explanation,
                    date = response.date,
                    imageUrl = response.url,
                    isAvailable = true
                )
            }
        } catch (e: Exception) {
            // Si falla la llamada optimizada, usar el método anterior como fallback
            getItemsByDateRangeFallback(startDate, endDate)
        }
    }
    
    /**
     * Método de fallback que hace llamadas individuales por fecha
     * Se usa cuando la función optimizada falla
     */
    private suspend fun getItemsByDateRangeFallback(startDate: LocalDate, endDate: LocalDate): List<NasaItem> {
        val items = mutableListOf<NasaItem>()
        var currentDate = startDate
        
        while (currentDate <= endDate) {
            try {
                val response = remoteDataSource.getAstronomyPictureOfTheDay(
                    apiKey, 
                    currentDate.toString()
                )
                items.add(NasaItem(
                    id = response.date,
                    title = response.title,
                    description = response.explanation,
                    date = response.date,
                    imageUrl = response.url,
                    isAvailable = true
                ))
            } catch (e: Exception) {
                // Fecha no disponible
                items.add(NasaItem(
                    id = currentDate.toString(),
                    title = "Fecha no disponible",
                    description = "No hay datos disponibles para esta fecha",
                    date = currentDate.toString(),
                    isAvailable = false
                ))
            }
            currentDate = currentDate.plusDays(1)
        }
        
        return items
    }
    
    override suspend fun getItemByDate(date: String): NasaItem {
        val response = remoteDataSource.getAstronomyPictureOfTheDay(apiKey, date)
        
        return NasaItem(
            id = response.date,
            title = response.title,
            description = response.explanation,
            date = response.date,
            imageUrl = response.url,
            isAvailable = true
        )
    }
}