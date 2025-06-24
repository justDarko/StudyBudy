package com.solo.userInterests.presentation.screen

import com.solo.core.domain.model.Interest

data class UserInterestsViewState(
    val interests: List<Interest> = emptyList(),
    val userSuccessfullySetInterests: Boolean = false,
    val userAlreadyHasInterests: Boolean = false,

)
