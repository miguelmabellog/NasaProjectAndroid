package com.migueldev.nasaproject.navigation

sealed class Screen(val route: String) {
    object Menu : Screen("menu")
    object PhotoOfDay : Screen("photo_of_day")
    object Asteroids : Screen("asteroids")
    object AsteroidsTodays : Screen("asteroids/todays")
    object AsteroidsHazardous : Screen("asteroids/hazardous")
    object AsteroidDetailTodays : Screen("asteroids/todays/detail/{asteroidId}") {
        fun createRoute(asteroidId: String) = "asteroids/todays/detail/$asteroidId"
    }
    object AsteroidDetailHazardous : Screen("asteroids/hazardous/detail/{asteroidId}") {
        fun createRoute(asteroidId: String) = "asteroids/hazardous/detail/$asteroidId"
    }
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: String) = "detail/$itemId"
    }
}
