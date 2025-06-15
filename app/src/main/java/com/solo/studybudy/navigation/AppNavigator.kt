package com.solo.studybudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solo.home.screen.HomeScreen
import com.solo.login.screen.loginScreen.LoginScreen
import com.solo.login.screen.register.RegisterScreen

@Composable
fun AppNavigator(
    isUserLoggedIn: Boolean = false
) {
    val navController = rememberNavController()

    val startDestination = if (isUserLoggedIn) {
        Route.HomeScreen
    } else {
        Route.LoginScreen
    }
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable<Route.LoginScreen> {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Route.RegisterScreen) },
                onSuccessfulLogin = { navController.navigate(Route.HomeScreen) })
        }
        composable<Route.RegisterScreen> {
            RegisterScreen(
                onSuccessfulRegistration = {
                    navController.popBackStack()
                }
            )
        }
        composable<Route.HomeScreen> {
            HomeScreen()
        }
    }
}