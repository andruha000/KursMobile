package com.mobileapp.kurs.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapp.kurs.data.model.City
import com.mobileapp.kurs.data.model.toEntity
import com.mobileapp.kurs.data.model.toModel
import com.mobileapp.kurs.data.repository.CityRepository
import com.mobileapp.kurs.data.repository.WeatherRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _cities = MutableStateFlow<List<City>>(emptyList())

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init { getCities() }

    private fun getCities() {
        viewModelScope.launch {
            cityRepository.getAllCities().collectLatest { entities ->
                val loadedCities = entities.map { it.toModel() }
                _cities.value = loadedCities
                loadAllWeather()
            }
        }
    }


    private fun loadAllWeather() {
        viewModelScope.launch {
            val cities = _cities.value

            if (cities.isEmpty()) {
                _uiState.value = MainUiState.Empty
                return@launch
            }

            _uiState.value = MainUiState.Loading

            try {
                val weatherByCity = cities.associateWith { city ->
                    async { weatherRepository.getWeather(city) }
                }.mapValues { (_, deferred) -> deferred.await() }

                val items = cities.map { city ->
                    val forecast = weatherByCity[city] ?: throw IllegalStateException("Missing forecast")
                    if (forecast.daily.isEmpty() || forecast.hourly.isEmpty()) {
                        throw IllegalStateException("Incomplete forecast")
                    }
                    CityWeatherItem(city = city, forecast = forecast)
                }

                _uiState.value = MainUiState.Content(items)
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e("MainViewModel", "Failed to load weather", exception)
                _uiState.value = MainUiState.Error
            }
        }
    }

    fun retryLoading() {
        loadAllWeather()
    }

    fun deleteCity(city: City) {
        viewModelScope.launch {
            cityRepository.deleteCity(city.toEntity())
        }
    }

    fun addCity(city: City) {
        if (_cities.value.any { it.name.equals(city.name, ignoreCase = true) }) {
            return
        }
        viewModelScope.launch {
            cityRepository.addCity(city.toEntity())
        }
    }
}
