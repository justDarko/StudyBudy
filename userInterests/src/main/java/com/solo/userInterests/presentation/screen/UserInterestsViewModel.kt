package com.solo.userInterests.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.core.CustomResult
import com.solo.core.domain.useCase.GetInterestsUseCase
import com.solo.core.domain.useCase.GetUserInterestsUseCase
import com.solo.core.domain.useCase.SetUserInterestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserInterestsViewModel @Inject constructor(
    private val getInterestsUseCase: GetInterestsUseCase,
    private val setUserInterestsUseCase: SetUserInterestsUseCase,
    private val getUserInterestsUseCase: GetUserInterestsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UserInterestsViewState())
    val state = _state.asStateFlow()

    init {
        onAction(UserInterestsActions.GetUsersInterests)
    }

    fun onAction(action: UserInterestsActions) {
        when (action) {
            is UserInterestsActions.SetUserInterestsAndJobTitle -> {
                setUserInterestsAndJobTitle(
                    interests = action.interests, jobTitle = action.jobTitle
                )
            }

            UserInterestsActions.GetUsersInterests -> getUserInterests()
        }
    }

    private fun getInterests() {
        viewModelScope.launch(Dispatchers.IO) {
            getInterestsUseCase().collectLatest { result ->
                when (result) {
                    is CustomResult.Success -> {
                        _state.update {
                            it.copy(
                                interests = result.data
                            )
                        }
                    }

                    is CustomResult.Failure -> {

                    }
                }
            }
        }
    }

    private fun getUserInterests() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getUserInterestsUseCase()) {
                is CustomResult.Success -> {
                    if (!result.data) getInterests()
                    else _state.update {
                        it.copy(
                            userAlreadyHasInterests = true
                        )
                    }
                }

                is CustomResult.Failure -> {
                    Timber.d("Result is failure: $result")
                }
            }
        }
    }

    private fun setUserInterestsAndJobTitle(interests: List<String>, jobTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = setUserInterestsUseCase(
                SetUserInterestsUseCase.Params(
                    jobTitle = jobTitle,
                    userInterests = interests,
                )
            )
            when (result) {
                is CustomResult.Success -> {
                    _state.update {
                        it.copy(
                            userSuccessfullySetInterests = true
                        )
                    }
                }

                is CustomResult.Failure -> {
                    Timber.d("Failed adding user interests.")
                }
            }
        }
    }

//    private fun tmpCoroutine() {
//        val ioCoroutineContext: CoroutineContext =
//            Dispatchers.IO + Job() + CoroutineName("IOCoroutine") + CoroutineExceptionHandler { _, _ ->
//
//            }
//
//        viewModelScope.launch(ioCoroutineContext) {
//            Timber.d("Hello before sleep")
//            delay(3000)
//            Timber.d("Hello after sleep")
//        }
//    }
}