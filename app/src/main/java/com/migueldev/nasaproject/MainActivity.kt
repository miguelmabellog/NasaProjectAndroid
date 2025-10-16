package com.migueldev.nasaproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.migueldev.nasaproject.navigation.Screen
import com.migueldev.nasaproject.presentation.MenuScreen
import com.migueldev.nasaproject.features.photoofday.presentation.ui.PhotoOfDayScreen
import com.migueldev.nasaproject.features.photoofday.presentation.ui.DetailScreen
import com.migueldev.nasaproject.features.asteroids.menu.presentation.ui.AsteroidsMenuScreen
import com.migueldev.nasaproject.features.asteroids.todays.presentation.ui.TodaysAsteroidsScreen
import com.migueldev.nasaproject.features.asteroids.todays.presentation.ui.TodaysAsteroidDetailScreen
import com.migueldev.nasaproject.features.asteroids.hazardous.presentation.ui.HazardousAsteroidsScreen
import com.migueldev.nasaproject.features.asteroids.hazardous.presentation.ui.HazardousAsteroidDetailScreen
import com.migueldev.nasaproject.preferences.PreferencesScreen
import com.migueldev.nasaproject.ui.theme.NasaProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NasaProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NasaApp()
                }
            }
        }
    }
}

@Composable
fun NasaApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route
    ) {
        composable(Screen.Menu.route) {
            MenuScreen(
                onNavigateToPhotoOfDay = {
                    navController.navigate(Screen.PhotoOfDay.route)
                },
                onNavigateToAsteroids = {
                    navController.navigate(Screen.Asteroids.route)
                },
                onNavigateToPreferences = {
                    navController.navigate(Screen.Preferences.route)
                }
            )
        }
        
        // Navegación a Photo of Day feature
        composable(Screen.PhotoOfDay.route) {
            PhotoOfDayScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDetail = { itemId ->
                    navController.navigate(Screen.Detail.createRoute(itemId))
                }
            )
        }
        
        // Navegación a Detail screen del feature
        composable(Screen.Detail.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            DetailScreen(
                itemId = itemId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        // Navegación a Asteroids feature - Menu
        composable(Screen.Asteroids.route) {
            AsteroidsMenuScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToUpcomingAsteroids = {
                    navController.navigate(Screen.AsteroidsTodays.route)
                },
                onNavigateToHazardousAsteroids = {
                    navController.navigate(Screen.AsteroidsHazardous.route)
                }
            )
        }
        
        // Navegación a Today's Asteroids
        composable(Screen.AsteroidsTodays.route) {
            TodaysAsteroidsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDetail = { asteroidId ->
                    navController.navigate(Screen.AsteroidDetailTodays.createRoute(asteroidId))
                }
            )
        }
        
        // Navegación a Today's Asteroid Detail
        composable(
            route = Screen.AsteroidDetailTodays.route,
            arguments = listOf(navArgument("asteroidId") { type = NavType.StringType })
        ) { backStackEntry ->
            val asteroidId = backStackEntry.arguments?.getString("asteroidId") ?: ""
            TodaysAsteroidDetailScreen(
                asteroidId = asteroidId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        // Navegación a Hazardous Asteroids
        composable(Screen.AsteroidsHazardous.route) {
            HazardousAsteroidsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDetail = { asteroidId ->
                    navController.navigate(Screen.AsteroidDetailHazardous.createRoute(asteroidId))
                }
            )
        }
        
        // Navegación a Hazardous Asteroid Detail
        composable(
            route = Screen.AsteroidDetailHazardous.route,
            arguments = listOf(navArgument("asteroidId") { type = NavType.StringType })
        ) { backStackEntry ->
            val asteroidId = backStackEntry.arguments?.getString("asteroidId") ?: ""
            HazardousAsteroidDetailScreen(
                asteroidId = asteroidId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        // Navegación a Preferences
        composable(Screen.Preferences.route) {
            PreferencesScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
