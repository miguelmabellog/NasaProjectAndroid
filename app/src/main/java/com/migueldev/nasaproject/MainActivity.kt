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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.migueldev.nasaproject.navigation.Screen
import com.migueldev.nasaproject.presentation.MenuScreen
import com.migueldev.nasaproject.presentation.PhotoOfDayScreen
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
                }
            )
        }
        
        composable(Screen.PhotoOfDay.route) {
            PhotoOfDayScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
