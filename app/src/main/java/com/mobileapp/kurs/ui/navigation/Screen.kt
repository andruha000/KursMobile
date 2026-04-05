package com.mobileapp.kurs.ui.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Search: Screen("search")
    object CityDetail : Screen("main/{cityId}") {
        fun passCityId(cityId: Int): String = "main/$cityId"
    }
}