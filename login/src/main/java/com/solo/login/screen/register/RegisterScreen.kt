package com.solo.login.screen.register

import AuthTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegisterScreen(
    onSuccessfulRegistration: () -> Unit,
    viewModel: RegisterUserViewModel = hiltViewModel()
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTextField(value = firstName, label = "First name", onValueChange = {
            firstName = it
        })

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(value = lastName, label = "Last name", onValueChange = {
            lastName = it
        })

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(value = email, label = "Email", onValueChange = {
            email = it
        })

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(
            value = password, label = "Password", onValueChange = {
                password = it
            }, isPassword = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(
            value = confirmPassword, label = "Confirm password", onValueChange = {
                confirmPassword = it
            }, isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                confirmPassword.isNotBlank()
            ) {
                viewModel.onAction(
                    RegisterUserActions.RegisterUser(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                    )
                )
            }
        }) {
            Text(text = "Register")
        }

        if (state.value.userIsSuccessfullyRegistered) {
            onSuccessfulRegistration()
        }
    }
}