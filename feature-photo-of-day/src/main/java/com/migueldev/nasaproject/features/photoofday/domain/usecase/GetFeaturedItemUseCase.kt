package com.migueldev.nasaproject.features.photoofday.domain.usecase

import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem
import com.migueldev.nasaproject.features.photoofday.domain.repository.PhotoOfDayRepository
import javax.inject.Inject

/**
 * Use case for getting the featured item (today's photo)
 * This encapsulates the business logic for fetching the featured item
 */
class GetFeaturedItemUseCase @Inject constructor(
    private val repository: PhotoOfDayRepository
) {
    
    /**
     * Execute the use case to get today's featured item
     */
    suspend operator fun invoke(): Result<NasaItem> {
        return repository.getFeaturedItem()
    }
}
