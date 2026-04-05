package com.mobileapp.kurs.data.dto.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val currentWeather: CurrentWeatherDto?,
    @SerializedName("hourly")
    val hourlyForecast: HourlyForecastDto?,
    @SerializedName("daily")
    val dailyForecast: DailyForecastDto?
)

