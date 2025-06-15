package com.solo.login.screen.loginScreen

import AuthTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginUserViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onSuccessfulLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTextField(value = email, label = "Email", onValueChange = {
            email = it
        })

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(
            value = password, label = "Password", onValueChange = {
                password = it
            }, isPassword = true
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Don't have an account? ")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Register!")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToRegister() },
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (email.isNotBlank() &&
                password.isNotBlank()
            ) {
                viewModel.onAction(
                    LoginUserActions.LoginUser(
                        email = email,
                        password = password
                    )
                )
            }
        }) {
            Text(text = "Login")
        }
        if (state.value.loggedInUser != null) {
            onSuccessfulLogin()
        }
    }
}