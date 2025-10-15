package com.migueldev.nasaproject.features.asteroids.common.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NeoFeedResponseDto(
    @SerializedName("element_count")
    val elementCount: Int,
    @SerializedName("near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDto>>
)

