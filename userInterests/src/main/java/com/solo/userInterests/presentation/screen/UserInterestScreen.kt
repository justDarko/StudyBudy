package com.solo.userInterests.presentation.screen

import com.solo.core.presentation.components.TextFieldComponent
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.core.presentation.components.ActionButtonPositive
import com.solo.core.presentation.components.BorderTextComponent
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

    val chosenInterests = remember { mutableStateListOf<String>() }
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
                        val isSelected = chosenInterests.contains(interest.id)
                        BorderTextComponent(text = interest.name,
                            isSelected = isSelected,
                            onClick = {
                                if (isSelected) {
                                    chosenInterests.remove(interest.id)
                                } else {
                                    chosenInterests.add(interest.id)
                                }
                            })
                    }
                }
            }
        }

        // Fixed bottom button
        ActionButtonPositive(title = "Done",
            isEnabled = jobTitle.isNotBlank() && chosenInterests.isNotEmpty(),
            modifier = Modifier.align(Alignment.BottomCenter),
            onActionButton = {
                Timber.d("Chosen interests are: $chosenInterests")
                viewModel.onAction(
                    UserInterestsActions.SetUserInterestsAndJobTitle(
                        interests = chosenInterests, jobTitle = jobTitle
                    )
                )
            })

    }
}