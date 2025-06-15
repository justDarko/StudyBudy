package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.model.User
import com.solo.core.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<LoginUserUseCase.Params, User>() {

    override suspend operator fun invoke(params: Params): CustomResult<User> {
        return authRepository.loginUser(
            email = params.email, password = params.password
        )
    }

    data class Params(
        val email: String, val password: String
    )
}