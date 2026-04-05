package com.mobileapp.kurs.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobileapp.kurs.data.repository.CityRepository
import com.mobileapp.kurs.data.repository.WeatherRepository

object MainViewModelFactory {
    fun create(
        cityRepository: CityRepository,
        weatherRepository: WeatherRepository
    ): ViewModelProvider.Factory = viewModelFactory {
        initializer {
            MainViewModel(
                cityRepository = cityRepository,
                weatherRepository = weatherRepository
            )
        }
    }
}
