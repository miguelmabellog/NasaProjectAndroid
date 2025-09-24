package com.migueldev.nasaproject.domain.model

data class NasaItem(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String? = null,
    val isAvailable: Boolean = true
)



