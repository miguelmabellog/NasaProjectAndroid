package com.migueldev.nasaproject.features.asteroids.common.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EstimatedDiameterDto(
    @SerializedName("kilometers")
    val kilometers: DiameterRangeDto,
    @SerializedName("meters")
    val meters: DiameterRangeDto,
    @SerializedName("miles")
    val miles: DiameterRangeDto,
    @SerializedName("feet")
    val feet: DiameterRangeDto
)

data class DiameterRangeDto(
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double,
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double
)


