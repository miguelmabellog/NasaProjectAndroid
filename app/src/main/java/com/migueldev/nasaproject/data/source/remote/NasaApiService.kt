package com.migueldev.nasaproject.data.source.remote

import com.migueldev.nasaproject.data.source.remote.dto.NasaResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun getAstronomyPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String? = null
    ): NasaResponseDto

    @GET("planetary/apod")
    suspend fun getAstronomyPicturesInRange(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String? = null
    ): List<NasaResponseDto>
}



