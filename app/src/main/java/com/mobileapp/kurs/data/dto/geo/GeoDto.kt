package com.mobileapp.kurs.data.dto.geo

import com.google.gson.annotations.SerializedName

data class GeoDto(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("admin1")
    val region: String?,
    val country: String?,
)