package com.migueldev.nasaproject.domain.repository

import com.migueldev.nasaproject.domain.model.NasaItem

interface NasaRepository {
    suspend fun getFeaturedItem(): NasaItem
}



