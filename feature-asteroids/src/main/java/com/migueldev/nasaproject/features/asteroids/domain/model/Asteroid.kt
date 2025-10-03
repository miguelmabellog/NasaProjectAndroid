package com.migueldev.nasaproject.features.asteroids.domain.model

data class Asteroid(
    val id: String,
    val name: String,
    val diameter: Double,
    val distance: Double,
    val velocity: Double,
    val isHazardous: Boolean,
    val approachDate: String
)
