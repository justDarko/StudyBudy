package com.solo.userInterests.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.core.presentation.components.ActionButtonPositive
import com.solo.core.presentation.components.BorderTextComponent
import com.solo.core.presentation.components.TextFieldComponent
import com.solo.core.presentation.components.UserAvatarComponent
import timber.log.Timber

@Composable
fun UserInterestScreen(
    onSuccessfulAddedInterest: () -> Unit,
    viewModel: UserInterestsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val success = state.value.userAlreadyHasInterests || state.value.userSuccessfullySetInterests
    LaunchedEffect(success) {
        if (success) onSuccessfulAddedInterest()
    }
    var jobTitle by remember { mutableStateOf("") }

    val chosenInterest = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                val name = "Darko Angelovski"
                UserAvatarComponent(
                    title = "Welcome $name", subtitle = "Select interest of your choice"
                )
            }

            item {
                Spacer(Modifier.height(32.dp))
                Text(text = "Enter your job title")
                Spacer(Modifier.height(8.dp))
                TextFieldComponent(
                    value = jobTitle,
                    label = "Job title",
                    onValueChange = { jobTitle = it })
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Interests")
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.value.interests.forEach { interest ->
                        val isSelected = chosenInterest.equals(interest.name)
                        BorderTextComponent(text = interest.name,
                            isSelected = isSelected,
                            onClick = {
                                if (isSelected) {
                                    chosenInterest.value = ""
                                } else {
                                    chosenInterest.value = interest.id
                                }
                            })
                    }
                }
            }
        }

        // Fixed bottom button
        ActionButtonPositive(title = "Done",
            isEnabled = jobTitle.isNotBlank() && chosenInterest.value.isNotEmpty(),
            modifier = Modifier.align(Alignment.BottomCenter),
            onActionButton = {
                Timber.d("Chosen interests are: $chosenInterest")
                viewModel.onAction(
                    UserInterestsActions.SetUserInterestsAndJobTitle(
                        interest = chosenInterest.value, jobTitle = jobTitle
                    )
                )
            })

    }
}