package com.mobileapp.kurs.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mobileapp.kurs.R
import com.mobileapp.kurs.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    currentRoute: String?,
    cityTitle: String?,
    onSearchClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val showBackButton = currentRoute != Screen.Main.route
    val title = when (currentRoute) {
        Screen.Main.route -> stringResource(R.string.title_weather)
        Screen.Search.route -> stringResource(R.string.title_search_city)
        Screen.CityDetail.route -> cityTitle ?: stringResource(R.string.title_city)
        else -> stringResource(R.string.title_weather)
    }

    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.content_back))
                }
            }
        },
        actions = {
            if (currentRoute == Screen.Main.route) {
                IconButton(onClick = onSearchClick) {
                    Icon(Icons.Default.Add, contentDescription = stringResource(R.string.content_add_city))
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}
