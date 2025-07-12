package com.solo.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        when (val currentState = state.value) {
            HomeScreenViewState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeScreenViewState.UserList -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(currentState.userList,
                        key = { user -> user.id }
                    ) { user ->
                        UserCardComponent(
                            fullName = user.firstName + " " + user.lastName,
                            interest = user.userInterest,
                            onDialClick = {},
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            is HomeScreenViewState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error loading deals")
                }
            }
        }
    }
}