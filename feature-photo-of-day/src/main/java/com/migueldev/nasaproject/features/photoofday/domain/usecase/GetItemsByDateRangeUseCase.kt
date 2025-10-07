package com.migueldev.nasaproject.features.photoofday.domain.usecase

import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem
import com.migueldev.nasaproject.features.photoofday.domain.repository.PhotoOfDayRepository
import java.time.LocalDate
import javax.inject.Inject

/**
 * Use case for getting items within a date range
 * This encapsulates the business logic for fetching multiple items by date range
 */
class GetItemsByDateRangeUseCase @Inject constructor(
    private val repository: PhotoOfDayRepository
) {
    
    /**
     * Execute the use case to get items within a date range
     */
    suspend operator fun invoke(
        startDate: LocalDate,
        endDate: LocalDate
    ): Result<List<NasaItem>> {
        return repository.getItemsByDateRange(startDate, endDate)
    }
}
