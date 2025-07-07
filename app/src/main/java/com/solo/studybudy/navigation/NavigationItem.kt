package com.solo.studybudy.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Route
    // Later on, if feed is added as a screen, we can add badge number
)