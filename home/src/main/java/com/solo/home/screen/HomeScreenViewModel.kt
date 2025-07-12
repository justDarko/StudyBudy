package com.solo.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.core.CustomResult
import com.solo.core.domain.useCase.MatchUsersByInterestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val matchUsersByInterestUseCase: MatchUsersByInterestUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<HomeScreenViewState>(HomeScreenViewState.Loading)
    val state = _state.asStateFlow()

    init {
        getMatchingUsers()
    }

    private fun getMatchingUsers() {
        viewModelScope.launch {
            when (val result = matchUsersByInterestUseCase()) {
                is CustomResult.Success -> {
                    _state.value = HomeScreenViewState.UserList(userList = result.data)
                    Timber.d("Success data is: ${result.data}")
                }

                is CustomResult.Failure -> {
                    Timber.d("Failure: ${result.message}")
                    _state.value = HomeScreenViewState.Error(result.message)
                }
            }
        }
    }
}