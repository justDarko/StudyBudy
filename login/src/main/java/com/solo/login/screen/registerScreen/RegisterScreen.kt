package com.solo.login.screen.registerScreen

import com.solo.core.presentation.components.TextFieldComponent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.core.presentation.components.ActionButtonPositive

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

    val isFormValid = listOf(
        firstName, lastName, email, password, confirmPassword
    ).all { it.isNotBlank() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComponent(
            value = firstName,
            label = "First Name",
            onValueChange = { firstName = it },
            imeAction = ImeAction.Next
        )

        TextFieldComponent(
            value = lastName,
            label = "Last Name",
            onValueChange = { lastName = it },
            imeAction = ImeAction.Next
        )

        TextFieldComponent(
            value = email,
            label = "Email",
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

        TextFieldComponent(
            value = password,
            label = "Password",
            onValueChange = { password = it },
            isPassword = true,
            imeAction = ImeAction.Next
        )

        TextFieldComponent(
            value = confirmPassword,
            label = "Confirm Password",
            onValueChange = { confirmPassword = it },
            isPassword = true,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(8.dp))

        ActionButtonPositive(
            isEnabled = isFormValid,
            title = "Register",
            onActionButton = {
                viewModel.onAction(
                    RegisterUserActions.RegisterUser(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword
                    )
                )
            }
        )

        if (state.value.userIsSuccessfullyRegistered) {
            onSuccessfulRegistration()
        }
    }
}
