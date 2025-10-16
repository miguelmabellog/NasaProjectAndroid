package com.migueldev.nasaproject.features.asteroids.common.di

import com.migueldev.nasaproject.features.asteroids.common.data.repository.AsteroidsRepositoryImpl
import com.migueldev.nasaproject.features.asteroids.common.domain.repository.AsteroidsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AsteroidsModule {
    
    @Binds
    @Singleton
    abstract fun bindAsteroidsRepository(
        impl: AsteroidsRepositoryImpl
    ): AsteroidsRepository
}


