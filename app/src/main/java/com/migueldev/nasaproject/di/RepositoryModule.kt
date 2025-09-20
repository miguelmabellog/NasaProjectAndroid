package com.migueldev.nasaproject.di

import com.migueldev.nasaproject.data.repository.NasaRepositoryImpl
import com.migueldev.nasaproject.domain.repository.NasaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNasaRepository(
        nasaRepositoryImpl: NasaRepositoryImpl
    ): NasaRepository
}

