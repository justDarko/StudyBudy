package com.solo.studybudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.core.CustomResult
import com.solo.core.domain.useCase.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = updateUserUseCase()) {
                    is CustomResult.Success -> {
                        Timber.d("User is logged in: ${result.data}")
                        _state.value = MainViewState.User(user = result.data)
                    }

                    is CustomResult.Failure -> {
                        Timber.e(result.throwable, "Login state check failed: ${result.message}")
                        _state.value = MainViewState.Error(message = result.message)
                    }
                }
            }
        }
    }
}