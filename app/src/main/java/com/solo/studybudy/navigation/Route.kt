package com.solo.studybudy.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object LoginScreen : Route

    @Serializable
    data object RegisterScreen : Route

    @Serializable
    data object UserInterestsScreen : Route

    @Serializable
    data object HomeScreen : Route

    @Serializable
    data object FeedScreen : Route

    @Serializable
    data object SettingsScreen : Route
}
fun Route.shouldShowBottomBar(): Boolean {
    return this !is Route.LoginScreen &&
            this !is Route.RegisterScreen &&
            this !is Route.UserInterestsScreen
}