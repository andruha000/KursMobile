package com.mobileapp.kurs.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.ui.components.WeatherCard
import com.mobileapp.kurs.viewmodel.MainUiState
import com.mobileapp.kurs.viewmodel.MainViewModel

@Composable
fun CitiesScreen(
    onCityClick: (Int) -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        MainUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        MainUiState.Error -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.space_large_1)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_medium_2))
                ) {
                    Text(
                        text = stringResource(R.string.weather_load_error),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Button(onClick = viewModel::retryLoading) {
                        Text(stringResource(R.string.retry))
                    }
                }
            }
        }
        MainUiState.Empty -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.space_large_1)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_saved_cities),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        is MainUiState.Content -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.space_large_1))
            ) {
                items(state.items, key = { it.city.id }) { item ->
                    WeatherCard(
                        city = item.city,
                        forecast = item.forecast,
                        onClick = { onCityClick(item.city.id) },
                        onDelete = { viewModel.deleteCity(item.city) }
                    )
                }
            }
        }
    }
}
