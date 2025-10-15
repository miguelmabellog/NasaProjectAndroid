package com.migueldev.nasaproject.features.asteroids.common.data.mapper

import com.migueldev.nasaproject.features.asteroids.common.data.remote.dto.AsteroidDto
import com.migueldev.nasaproject.features.asteroids.common.data.remote.dto.NeoFeedResponseDto
import com.migueldev.nasaproject.features.asteroids.domain.model.Asteroid

object AsteroidMapper {
    
    fun toDomain(dto: AsteroidDto): Asteroid {
        val closeApproach = dto.closeApproachData.firstOrNull()
        
        return Asteroid(
            id = dto.id,
            name = dto.name.removePrefix("(").removeSuffix(")").trim(),
            diameter = dto.estimatedDiameter.kilometers.estimatedDiameterMax,
            distance = closeApproach?.missDistance?.astronomical?.toDoubleOrNull() ?: 0.0,
            velocity = closeApproach?.relativeVelocity?.kilometersPerSecond?.toDoubleOrNull() ?: 0.0,
            isHazardous = dto.isPotentiallyHazardousAsteroid,
            approachDate = closeApproach?.closeApproachDate ?: ""
        )
    }
    
    fun toDomainList(responseDto: NeoFeedResponseDto): List<Asteroid> {
        return responseDto.nearEarthObjects.values
            .flatten()
            .map { toDomain(it) }
    }
}

