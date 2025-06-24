package com.solo.login.screen.registerScreen

sealed interface RegisterUserActions {
    data class RegisterUser(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : RegisterUserActions

}