package com.migueldev.nasaproject.features.photoofday.domain.model

import java.time.LocalDate

/**
 * Domain model for NASA Photo of the Day
 * This represents the business entity in the domain layer
 */
data class NasaItem(
    val id: String = "", // Using date as ID for simplicity
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String,
    val copyright: String? = null,
    val isAvailable: Boolean = true // To handle cases where image/data might be missing
) {
    /**
     * Get the date as LocalDate for date operations
     */
    fun getLocalDate(): LocalDate {
        return LocalDate.parse(date)
    }
    
    /**
     * Check if this is a video media type
     */
    fun isVideo(): Boolean {
        return mediaType == "video"
    }
    
    /**
     * Get the display URL (prefer HD if available and not video)
     */
    fun getDisplayUrl(): String? {
        return if (!isVideo() && !hdurl.isNullOrEmpty()) {
            hdurl
        } else {
            url
        }
    }
    
    /**
     * Get the image URL for display
     */
    val imageUrl: String? = getDisplayUrl()
}
