package com.mobileapp.kurs.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobileapp.kurs.ui.components.TopBar
import com.mobileapp.kurs.ui.navigation.NavGraph
import com.mobileapp.kurs.ui.navigation.Screen
import com.mobileapp.kurs.viewmodel.MainUiState
import com.mobileapp.kurs.viewmodel.MainViewModel
import com.mobileapp.kurs.viewmodel.SearchViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val uiState by mainViewModel.uiState.collectAsState()

    val cityId = currentBackStackEntry?.arguments?.getInt("cityId")
    val cityTitle = (uiState as? MainUiState.Content)
        ?.items
        ?.firstOrNull { it.city.id == cityId }
        ?.city
        ?.name

    Scaffold(
        topBar = {
            TopBar(
                currentRoute = currentRoute,
                cityTitle = cityTitle,
                onSearchClick = {
                    navController.navigate(Screen.Search.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            mainViewModel = mainViewModel,
            searchViewModel = searchViewModel,
            modifier = modifier.padding(paddingValues)
        )
    }
}
