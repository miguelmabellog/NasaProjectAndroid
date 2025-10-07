package com.migueldev.nasaproject.features.photoofday.domain.repository

import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem
import java.time.LocalDate

/**
 * Repository interface for Photo of Day feature
 * This defines the contract for data access in the domain layer
 */
interface PhotoOfDayRepository {
    
    /**
     * Get the featured item (today's photo)
     */
    suspend fun getFeaturedItem(): Result<NasaItem>
    
    /**
     * Get a specific item by date
     */
    suspend fun getItemByDate(date: LocalDate): Result<NasaItem>
    
    /**
     * Get items within a date range
     */
    suspend fun getItemsByDateRange(
        startDate: LocalDate,
        endDate: LocalDate
    ): Result<List<NasaItem>>
}
