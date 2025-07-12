package com.solo.home.screen

import com.solo.core.domain.model.User

sealed class HomeScreenViewState {
    data class UserList(val userList: List<User>) : HomeScreenViewState()
    data object Loading : HomeScreenViewState()
    data class Error(val message: String) : HomeScreenViewState()
}