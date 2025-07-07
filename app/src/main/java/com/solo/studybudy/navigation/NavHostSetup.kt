package com.solo.studybudy.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.solo.core.domain.model.User
import com.solo.home.screen.HomeScreen
import com.solo.login.screen.loginScreen.LoginScreen
import com.solo.login.screen.registerScreen.RegisterScreen
import com.solo.userInterests.presentation.screen.UserInterestScreen

@Composable
fun NavHostSetup(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: Route,
    user: User?
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable<Route.LoginScreen> {
            LoginScreen(onNavigateToRegister = { navController.navigate(Route.RegisterScreen) },
                onSuccessfulLogin = {
                    navController.navigate(
                        if (user?.userInterests?.isNotEmpty() == true) Route.HomeScreen else Route.UserInterestsScreen
                    )
                })
        }
        composable<Route.RegisterScreen> {
            RegisterScreen(onSuccessfulRegistration = {
                navController.popBackStack()
            })
        }
        composable<Route.UserInterestsScreen> {
            UserInterestScreen(onSuccessfulAddedInterest = {
                navController.navigate(Route.HomeScreen)
            })
        }
        composable<Route.HomeScreen> {
            HomeScreen(modifier = modifier)
        }
        composable<Route.FeedScreen> {
            FeedScreen(modifier = modifier)
        }
        composable<Route.SettingsScreen> {
            SettingsScreen(modifier = modifier)
        }
    }
}

@Composable
fun FeedScreen(
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Feed")
    }
}

@Composable
fun SettingsScreen(
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings")
    }
}