package com.solo.login.screen.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.core.CustomResult
import com.solo.core.domain.useCase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginUserViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUserViewState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginUserActions) {
        when (action) {
            is LoginUserActions.LoginUser -> loginUser(
                email = action.email,
                password = action.password
            )
        }
    }

    private fun loginUser(
        email: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginUserUseCase(
                LoginUserUseCase.Params(
                    email = email,
                    password = password
                )
            )

            when (result) {
                is CustomResult.Success -> {
                    _state.update {
                        it.copy(
                            loggedInUser = result.data
                        )
                    }
                }

                is CustomResult.Failure -> {

                }
            }
        }
    }
}