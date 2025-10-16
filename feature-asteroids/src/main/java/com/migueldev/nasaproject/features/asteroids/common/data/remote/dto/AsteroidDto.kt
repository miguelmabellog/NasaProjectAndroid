package com.migueldev.nasaproject.features.asteroids.common.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AsteroidDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nasa_jpl_url")
    val nasaJplUrl: String,
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double,
    @SerializedName("estimated_diameter")
    val estimatedDiameter: EstimatedDiameterDto,
    @SerializedName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean,
    @SerializedName("close_approach_data")
    val closeApproachData: List<CloseApproachDataDto>
)


