package com.migueldev.nasaproject.core.network.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J4\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\n\u001a\u00020\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/migueldev/nasaproject/core/network/api/NasaApiService;", "", "getAstronomyPictureOfTheDay", "Lcom/migueldev/nasaproject/core/network/dto/NasaResponseDto;", "apiKey", "", "date", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAstronomyPicturesInRange", "", "startDate", "endDate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core-network_debug"})
public abstract interface NasaApiService {
    
    @retrofit2.http.GET(value = "planetary/apod")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAstronomyPictureOfTheDay(@retrofit2.http.Query(value = "api_key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @retrofit2.http.Query(value = "date")
    @org.jetbrains.annotations.Nullable()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.migueldev.nasaproject.core.network.dto.NasaResponseDto> $completion);
    
    @retrofit2.http.GET(value = "planetary/apod")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAstronomyPicturesInRange(@retrofit2.http.Query(value = "api_key")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @retrofit2.http.Query(value = "start_date")
    @org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @retrofit2.http.Query(value = "end_date")
    @org.jetbrains.annotations.Nullable()
    java.lang.String endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.migueldev.nasaproject.core.network.dto.NasaResponseDto>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}