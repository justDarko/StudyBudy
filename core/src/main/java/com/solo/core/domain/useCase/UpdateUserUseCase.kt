package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.model.User
import com.solo.core.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

class UpdateUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCaseNoParams<User>() {

    override suspend fun invoke(): CustomResult<User> {
        return authRepository.getUser()
    }
}