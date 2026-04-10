package com.mobileapp.kurs.data.model

data class City(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val region: String? = null,
    val country: String? = null
)
