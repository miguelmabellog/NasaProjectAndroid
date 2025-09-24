package com.migueldev.nasaproject.domain.repository

import com.migueldev.nasaproject.domain.model.NasaItem
import java.time.LocalDate

interface NasaRepository {
    suspend fun getFeaturedItem(): NasaItem
    suspend fun getItemsByDateRange(startDate: LocalDate, endDate: LocalDate): List<NasaItem>
    suspend fun getItemByDate(date: String): NasaItem
}



