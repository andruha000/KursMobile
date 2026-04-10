package com.mobileapp.kurs.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.data.model.HourlyWeather

@Composable
fun HourlyForecastSection(
    hourlyItems: List<HourlyWeather>,
    currentTime: String,
    modifier: Modifier = Modifier
) {
    val visibleItems = getNextHours(hourlyItems, currentTime)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_radius_large)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_medium_3))
        ) {
            Text(
                text = stringResource(R.string.section_hourly_forecast),
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.space_large_2),
                    top = dimensionResource(R.dimen.space_large_2),
                    end = dimensionResource(R.dimen.space_large_2)
                ),
                style = MaterialTheme.typography.titleMedium
            )
            HorizontalDivider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_medium_2)),
                contentPadding = PaddingValues(
                    start = dimensionResource(R.dimen.space_large_2),
                    end = dimensionResource(R.dimen.space_large_2),
                    bottom = dimensionResource(R.dimen.space_large_2)
                )
            ) {
                itemsIndexed(visibleItems, key = { _, item -> item.time }) { index, hour ->
                    Card(
                        modifier = Modifier.width(dimensionResource(R.dimen.weather_card_info_height)),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.card_radius_medium)),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                vertical = dimensionResource(R.dimen.space_medium_3),
                                horizontal = dimensionResource(R.dimen.space_medium_1)
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small_3))
                        ) {
                            Text(
                                text = if (index == 0) {
                                    stringResource(R.string.label_now)
                                } else {
                                    formatHour(hour.time)
                                },
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = getWeatherIcon(hour.weatherCode),
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = stringResource(R.string.format_temperature, hour.temperature.toInt()),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getNextHours( hourlyItems: List<HourlyWeather>, currentTime: String): List<HourlyWeather> {
    val startIndex = hourlyItems.indexOfLast { it.time <= currentTime }.let { index ->
        if (index >= 0) index else 0
    }
    return hourlyItems.drop(startIndex).take(12)
}

private fun formatHour(value: String): String {
    return value.substring(11, 13)
}
