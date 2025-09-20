package com.migueldev.nasaproject.domain.usecase

import com.migueldev.nasaproject.domain.model.NasaItem
import com.migueldev.nasaproject.domain.repository.NasaRepository
import javax.inject.Inject

class GetFeaturedItemUseCase @Inject constructor(
    private val repository: NasaRepository
) {
    suspend operator fun invoke(): NasaItem = repository.getFeaturedItem()
}



