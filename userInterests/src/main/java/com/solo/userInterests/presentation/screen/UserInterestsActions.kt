package com.solo.userInterests.presentation.screen

sealed interface UserInterestsActions {
    data class SetUserInterestsAndJobTitle(val interest: String, val jobTitle: String) :
        UserInterestsActions

    data object GetUsersInterests : UserInterestsActions
}