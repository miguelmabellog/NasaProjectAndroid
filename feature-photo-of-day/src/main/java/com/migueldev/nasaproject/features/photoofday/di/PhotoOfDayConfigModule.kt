package com.migueldev.nasaproject.features.photoofday.di

import com.migueldev.nasaproject.core.common.di.NasaApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Configuration module for Photo of Day feature
 * This provides configuration values needed by the feature
 */
@Module
@InstallIn(SingletonComponent::class)
object PhotoOfDayConfigModule {
    
    /**
     * Provide NASA API key for the Photo of Day feature
     * This will be provided by the app module
     */
    @Provides
    @Singleton
    @NasaApiKey
    fun provideNasaApiKey(): String {
        // This will be overridden by the app module
        return "DEMO_KEY"
    }
}
