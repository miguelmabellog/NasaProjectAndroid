package com.migueldev.nasaproject.features.photoofday.data.mapper

import com.migueldev.nasaproject.core.network.dto.NasaResponseDto
import com.migueldev.nasaproject.features.photoofday.domain.model.NasaItem

/**
 * Mapper to convert between DTOs and domain models
 * This handles the transformation between data layer and domain layer
 */
object NasaMapper {
    
    /**
     * Convert NasaResponseDto to NasaItem domain model
     */
    fun toDomainModel(dto: NasaResponseDto): NasaItem {
        return NasaItem(
            id = dto.date, // Using date as ID
            date = dto.date,
            explanation = dto.explanation,
            hdurl = dto.hdUrl,
            mediaType = dto.mediaType,
            serviceVersion = dto.serviceVersion,
            title = dto.title,
            url = dto.url,
            copyright = dto.copyright,
            isAvailable = true
        )
    }
    
    /**
     * Convert list of NasaResponseDto to list of NasaItem domain models
     */
    fun toDomainModelList(dtoList: List<NasaResponseDto>): List<NasaItem> {
        return dtoList.map { toDomainModel(it) }
    }
}
