package com.solo.studybudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.core.CustomResult
import com.solo.core.domain.useCase.IsUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow<Boolean?>(null)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = isUserLoggedInUseCase()) {
                is CustomResult.Success -> {
                    Timber.d("User is logged in: ${result.data}")
                    _isUserLoggedIn.value = result.data
                }

                is CustomResult.Failure -> {
                    _isUserLoggedIn.value = false
                    Timber.e(result.throwable, "Login state check failed: ${result.message}")
                }
            }
        }
    }
}