package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsUserLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCaseNoParams<Boolean>() {

    override suspend fun invoke(): CustomResult<Boolean> {
        return authRepository.isUserLoggedIn()
    }
}