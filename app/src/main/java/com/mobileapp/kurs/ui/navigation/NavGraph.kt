package com.mobileapp.kurs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mobileapp.kurs.ui.screens.*
import com.mobileapp.kurs.viewmodel.MainViewModel
import com.mobileapp.kurs.viewmodel.SearchViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier
    ) {


        composable(Screen.Main.route) {
            CitiesScreen(
                onCityClick = { cityId ->
                    navController.navigate(Screen.CityDetail.passCityId(cityId))
                },
                viewModel = mainViewModel
            )
        }


        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = searchViewModel,
                onAddCity = { city -> mainViewModel.addCity(city) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.CityDetail.route,
            arguments = listOf(
                navArgument("cityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getInt("cityId") ?: return@composable
            CityScreen(
                cityId = cityId,
                viewModel = mainViewModel
            )
        }
    }
}
