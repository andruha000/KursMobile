package com.mobileapp.kurs.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.data.model.City
import com.mobileapp.kurs.data.model.WeatherForecast

@Composable
fun WeatherCard(
    city: City,
    forecast: WeatherForecast,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dayForecast = forecast.daily.first()
    val locationLine = listOfNotNull(city.region, city.country).joinToString(stringResource(R.string.separator))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.space_large_1),
                vertical = dimensionResource(R.dimen.space_small_2)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_radius_small)),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.space_small_1)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.space_large_1)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(dimensionResource(R.dimen.weather_card_info_height))
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = city.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    if (locationLine.isNotBlank()) {
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_small_1)))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.space_small_1)))
                            Text(
                                text = locationLine,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = dimensionResource(R.dimen.space_small_1))
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.content_delete_city),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.space_medium_2)))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = stringResource(R.string.format_temperature, forecast.current.temperature.toInt()),
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_small_3)))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(
                            R.string.format_temperature_max,
                            dayForecast.temperatureMax.toInt().toString()
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.space_small_3)))
                    Text(
                        text = stringResource(
                            R.string.format_temperature_min,
                            dayForecast.temperatureMin.toInt().toString()
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_small_3)))
                Text(
                    text = getWeatherDescription(forecast.current.weatherCode),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
