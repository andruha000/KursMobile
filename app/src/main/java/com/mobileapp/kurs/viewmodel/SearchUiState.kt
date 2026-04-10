package com.mobileapp.kurs.viewmodel

import com.mobileapp.kurs.data.dto.geo.GeoDto

sealed interface SearchUiState {
    data object Idle : SearchUiState
    data object Loading : SearchUiState
    data object Error : SearchUiState
    data object Empty : SearchUiState
    data class Content(val cities: List<GeoDto>) : SearchUiState
}
