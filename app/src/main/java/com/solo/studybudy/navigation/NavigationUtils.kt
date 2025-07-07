package com.solo.studybudy.navigation

fun String?.toRoute(): Route = when (this) {
//    "com.solo.studybudy.navigation.Route.LoginScreen" -> Route.LoginScreen
//    "com.solo.studybudy.navigation.Route.RegisterScreen" -> Route.RegisterScreen
//    "com.solo.studybudy.navigation.Route.UserInterestsScreen" -> Route.UserInterestsScreen
    "com.solo.studybudy.navigation.Route.HomeScreen" -> Route.HomeScreen
    "com.solo.studybudy.navigation.Route.FeedScreen" -> Route.FeedScreen
    "com.solo.studybudy.navigation.Route.SettingsScreen" -> Route.SettingsScreen
    else -> Route.LoginScreen // fallback or unknown route handler
}