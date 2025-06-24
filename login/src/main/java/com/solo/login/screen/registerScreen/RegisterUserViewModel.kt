package com.solo.login.screen.registerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.core.CustomResult
import com.solo.core.domain.useCase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUserViewState())
    val state = _state.asStateFlow()

    fun onAction(action: RegisterUserActions) {
        when (action) {
            is RegisterUserActions.RegisterUser -> registerUser(
                firstName = action.firstName,
                lastName = action.lastName,
                email = action.email,
                password = action.password,
                confirmPassword = action.confirmPassword,
            )
        }
    }

    private fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = registerUserUseCase(
                RegisterUserUseCase.Params(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    confirmPassword = confirmPassword,
                )
            )

            when (result) {
                is CustomResult.Success -> {
                    _state.update {
                        it.copy(
                            userIsSuccessfullyRegistered = true
                        )
                    }
                }

                is CustomResult.Failure -> {

                }
            }
        }
    }
}