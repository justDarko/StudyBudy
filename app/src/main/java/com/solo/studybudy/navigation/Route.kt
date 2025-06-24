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
}