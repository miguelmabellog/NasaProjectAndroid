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
     * Provide NASA API key from BuildConfig
     * Reads from local.properties: llave-personalizada
     */
    @Provides
    @Singleton
    @NasaApiKey
    fun provideNasaApiKey(): String {
        return com.migueldev.nasaproject.features.photoofday.BuildConfig.NASA_API_KEY
    }
}
