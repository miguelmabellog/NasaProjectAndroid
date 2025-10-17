package com.migueldev.nasaproject.features.photoofday.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.nasaproject.features.photoofday.presentation.ui.DetailScreen
import com.migueldev.nasaproject.features.photoofday.presentation.ui.PhotoOfDayScreen

/**
 * Navigation functions for Photo of Day feature
 * This exposes the UI screens to the app module
 */
object PhotoOfDayNavigation {

    @Composable
    fun PhotoOfDayScreen(
        onNavigateBack: () -> Unit,
        onNavigateToDetail: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        com.migueldev.nasaproject.features.photoofday.presentation.ui.PhotoOfDayScreen(
            onNavigateBack = onNavigateBack,
            onNavigateToDetail = onNavigateToDetail,
            modifier = modifier
        )
    }
    
    @Composable
    fun DetailScreen(
        itemId: String,
        onNavigateBack: () -> Unit,
//        modifier: Modifier = Modifier
    ) {
        com.migueldev.nasaproject.features.photoofday.presentation.ui.DetailScreen(
            itemId = itemId,
            onNavigateBack = onNavigateBack,
            // modifier = modifier
        )
    }
}
