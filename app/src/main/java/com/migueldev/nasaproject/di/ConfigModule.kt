package com.migueldev.nasaproject.di

import com.migueldev.nasaproject.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NasaApiKey

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @NasaApiKey
    fun provideNasaApiKey(): String = BuildConfig.NASA_API_KEY
}
