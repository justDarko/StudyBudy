package com.solo.studybudy

sealed class MainViewState {
    data class User(val user: com.solo.core.domain.model.User?) : MainViewState()
    data object Loading : MainViewState()
    data class Error(val message: String) : MainViewState()
}