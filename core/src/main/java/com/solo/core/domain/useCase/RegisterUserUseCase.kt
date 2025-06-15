package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<RegisterUserUseCase.Params, Boolean>() {

    override suspend operator fun invoke(params: Params): CustomResult<Boolean> {
        return authRepository.registerUser(
            firstName = params.firstName,
            lastName = params.lastName,
            email = params.email,
            password = params.password,
            confirmPassword = params.confirmPassword,
        )
    }

    data class Params(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    )
}