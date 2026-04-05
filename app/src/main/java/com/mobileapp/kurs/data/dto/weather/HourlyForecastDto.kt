package com.mobileapp.kurs.data.dto.weather

import com.google.gson.annotations.SerializedName

data class HourlyForecastDto(
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperature: List<Double>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
)