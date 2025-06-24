package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInterestsUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCaseNoParams<Boolean>() {

    override suspend fun invoke(): CustomResult<Boolean> {
        return userRepository.checkIfUserSetInterests()
    }
}