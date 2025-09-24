package com.migueldev.nasaproject.navigation

sealed class Screen(val route: String) {
    object Menu : Screen("menu")
    object PhotoOfDay : Screen("photo_of_day")
}
