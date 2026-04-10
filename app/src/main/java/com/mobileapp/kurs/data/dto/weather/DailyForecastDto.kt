package com.mobileapp.kurs.data.dto.weather

import com.google.gson.annotations.SerializedName

data class DailyForecastDto(
    val time: List<String>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>,
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>
)