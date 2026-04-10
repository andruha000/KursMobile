package com.mobileapp.kurs.data.net

import com.mobileapp.kurs.data.dto.geo.GeoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApi {

    @GET("v1/search")
    suspend fun searchCities(
        @Query("name") name: String,
        @Query("count") count: Int,
        @Query("language") language: String = "ru"
    ): GeoResponse
}