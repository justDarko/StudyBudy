package com.solo.studybudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solo.login.screen.loginScreen.LoginScreen
import com.solo.login.screen.register.RegisterScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Route.LoginScreen
    ) {
        composable<Route.LoginScreen> {
            LoginScreen(navController = navController,
                onNavigateToRegister = { navController.navigate(Route.RegisterScreen) })
        }
        composable<Route.RegisterScreen> {
            RegisterScreen()
        }
    }
}