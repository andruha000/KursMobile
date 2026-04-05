package com.mobileapp.kurs.viewmodel

import com.mobileapp.kurs.data.model.City
import com.mobileapp.kurs.data.model.WeatherForecast

data class CityWeatherItem(
    val city: City,
    val forecast: WeatherForecast
)

sealed interface MainUiState {
    data object Loading : MainUiState
    data object Error : MainUiState
    data object Empty : MainUiState
    data class Content(val items: List<CityWeatherItem>) : MainUiState
}
