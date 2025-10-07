package com.migueldev.nasaproject.core.network.dto

import com.google.gson.annotations.SerializedName

data class NasaResponseDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdurl")
    val hdUrl: String?,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("copyright")
    val copyright: String? = null
)

