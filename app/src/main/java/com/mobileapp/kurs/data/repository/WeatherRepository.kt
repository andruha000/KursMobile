package com.mobileapp.kurs.data.repository

import com.mobileapp.kurs.data.model.City
import com.mobileapp.kurs.data.model.WeatherForecast
import com.mobileapp.kurs.data.model.toModel
import com.mobileapp.kurs.data.net.WeatherApi

class WeatherRepository(
    private val weatherApi: WeatherApi
) {
    suspend fun getWeather(city: City): WeatherForecast {
        return weatherApi.getForecast(
            latitude = city.latitude,
            longitude = city.longitude,
            forecastDays = 7
        ).toModel()
    }
}
