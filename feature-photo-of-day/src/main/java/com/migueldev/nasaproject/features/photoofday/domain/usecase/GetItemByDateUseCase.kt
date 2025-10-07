package com.migueldev.nasaproject.features.photoofday.domain.usecase

import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem
import com.migueldev.nasaproject.features.photoofday.domain.repository.PhotoOfDayRepository
import java.time.LocalDate
import javax.inject.Inject

/**
 * Use case for getting a specific item by date
 * This encapsulates the business logic for fetching an item by date
 */
class GetItemByDateUseCase @Inject constructor(
    private val repository: PhotoOfDayRepository
) {
    
    /**
     * Execute the use case to get an item by specific date
     */
    suspend operator fun invoke(date: LocalDate): Result<NasaItem> {
        return repository.getItemByDate(date)
    }
}
