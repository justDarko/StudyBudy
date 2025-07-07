package com.solo.studybudy.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solo.core.domain.model.User
import timber.log.Timber

@Composable
fun AppNavigator(
    user: User?
) {
    val startDestination = if (user?.id?.isNotBlank() == true) {
        // Check if he set interests.
        if (user.userInterests.isEmpty())
            Route.UserInterestsScreen
        else Route.HomeScreen
    } else {
        Route.LoginScreen
    }

    val navController = rememberNavController()

    val navigationMenu = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Route.HomeScreen
        ),
        NavigationItem(
            title = "Feed",
            selectedIcon = Icons.Filled.PostAdd,
            unselectedIcon = Icons.Outlined.PostAdd,
            route = Route.FeedScreen
        ),
//        NavigationItem(
//            title = "Home",
//            selectedIcon = Icons.Filled.Home,
//            unselectedIcon = Icons.Outlined.Home,
//            route = Route.HomeScreen
//        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            route = Route.SettingsScreen
        ),
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    Timber.d("Arguments route destination: ${currentBackStackEntry?.destination?.route}")

    val currentRoute = currentBackStackEntry?.destination?.route.toRoute()

    Timber.d("Current route is: $currentRoute")

    val indexOnSelectedItem = when (currentRoute) {
        is Route.HomeScreen -> 0
        is Route.FeedScreen -> 1
        is Route.SettingsScreen -> 2
        else -> -1
    }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (currentRoute.shouldShowBottomBar()) {
            NavigationBar {
                navigationMenu.fastForEachIndexed { index, item ->
                    NavigationBarItem(selected = indexOnSelectedItem == index, onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }, icon = {
                        if (indexOnSelectedItem == index) Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = "Selected Tab Icon"
                        )
                        else Icon(
                            imageVector = item.unselectedIcon,
                            contentDescription = "Unselected Tab Icon"
                        )
                    }, label = {
                        Text(text = item.title)
                    })
                }

            }
        }
    }) { contentPadding ->
        NavHostSetup(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            navController = navController,
            startDestination = startDestination,
            user = user
        )
    }
}