package com.migueldev.nasaproject.features.photoofday.di

import com.migueldev.nasaproject.features.photoofday.data.repository.PhotoOfDayRepositoryImpl
import com.migueldev.nasaproject.features.photoofday.data.source.PhotoOfDayRemoteDataSource
import com.migueldev.nasaproject.features.photoofday.domain.repository.PhotoOfDayRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for Photo of Day feature
 * This provides all the dependencies needed for the Photo of Day feature
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class PhotoOfDayModule {
    
    /**
     * Bind PhotoOfDayRepository implementation
     */
    @Binds
    @Singleton
    abstract fun bindPhotoOfDayRepository(
        photoOfDayRepositoryImpl: PhotoOfDayRepositoryImpl
    ): PhotoOfDayRepository
}
