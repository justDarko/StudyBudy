package com.solo.login.screen.loginScreen

sealed interface LoginUserActions {
    data class LoginUser(
        val email: String,
        val password: String
    ) : LoginUserActions

}