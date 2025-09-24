package com.migueldev.nasaproject.domain.usecase

import com.migueldev.nasaproject.domain.model.NasaItem
import com.migueldev.nasaproject.domain.repository.NasaRepository
import javax.inject.Inject

class GetItemByDateUseCase @Inject constructor(
    private val repository: NasaRepository
) {
    suspend operator fun invoke(date: String): NasaItem = repository.getItemByDate(date)
}
