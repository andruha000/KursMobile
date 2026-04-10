package com.mobileapp.kurs.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapp.kurs.data.repository.CityRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SearchViewModel(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onQueryChanged(value: String) {
        _query.value = value
        searchJob?.cancel()

        if (value.isBlank()) {
            _uiState.value = SearchUiState.Idle
            return
        }

        _uiState.value = SearchUiState.Loading

        searchJob = viewModelScope.launch {
            delay(400.milliseconds)
            try {
                val cities = cityRepository.searchCities(value.trim())
                _uiState.value = if (cities.isEmpty()) {
                    SearchUiState.Empty
                } else {
                    SearchUiState.Content(cities)
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e("SearchViewModel", "Failed to query", exception)
                _uiState.value = SearchUiState.Error
            }

        }
    }

    fun reset() {
        _query.value = ""
        _uiState.value = SearchUiState.Idle
    }
}
